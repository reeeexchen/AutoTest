package top.rex.code.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.rex.code.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/11 0:16
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    // 获取执行SQL的对象
    @Autowired
    private SqlSessionTemplate template;

    @ApiOperation(value = "登录接口")
    @PostMapping("/login")
    public Boolean loginUser(HttpServletResponse response, @RequestBody User user) {
        Boolean result = (Integer) template.selectOne("loginUser", user) == 1;
        log.info("查询结果为：" + result);
        if (result) {
            Cookie cookie = new Cookie("login", "true");
            response.addCookie(cookie);
            log.info("用户是：" + user.getUsername());
        }
        return result;
    }

    @ApiOperation(value = "添加用户接口")
    @PostMapping("/add")
    public Boolean addUser(HttpServletRequest request, @RequestBody User user) {
        Boolean loginV = verifyCookies(request);
        int result = 0;
        if (loginV) {
            result = template.insert("addUser", user);
        }
        if (1 == result) {
            log.info("添加用户成功：" + user.toString());
        }
        return result == 1;
    }

    @ApiOperation(value = "获取用户信息(列表或指定)接口")
    @PostMapping("/list")
    public List<User> getUserInfo(HttpServletRequest request, @RequestBody User user) {
        Boolean loginV = verifyCookies(request);
        List<User> result = null;
        if (loginV) {
            result = template.selectList("getUserInfo", user);
            log.info("获取到用户数量为：" + result.size());
        }
        return result;
    }

    @ApiOperation(value = "更新（或删除）用户接口")
    @PostMapping("/update")
    public int updateUser(HttpServletRequest request, @RequestBody User user) {
        Boolean loginV = verifyCookies(request);
        int result = 0;
        if (loginV) {
            result = template.update("updateUser", user);

        }
        if (1 == result) {
            log.info("成功更新用户信息：" + user.toString());
        }
        return result;
    }

    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (ObjectUtils.isEmpty(cookies)) {
            log.info("Cookies为空");
            return false;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                log.info("Cookies验证通过");
                return true;
            }
        }
        return false;
    }
}
