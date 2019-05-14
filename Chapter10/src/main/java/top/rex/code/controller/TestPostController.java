package top.rex.code.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.rex.code.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/9 21:49
 * @Version: 1.0
 */
@RestController
@RequestMapping("/post")
public class TestPostController {
    private static Cookie cookie;

    // 用户成功获取Cookies，然后再访问其它接口获取用户列表
    @PostMapping("/login")
    @ApiOperation(value = "登录接口，成功后获取Cookies信息")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "password", required = true) String password) {
        if (username.equals("rex") && password.equals("123456")) {
            cookie = new Cookie("login", "true");
            response.addCookie(cookie);
            return "SUCCESS LOGIN";
        }
        return "用户名或密码错误";
    }

    // 验证Cookies，返回用户列表
    @PostMapping("/user/list")
    @ApiOperation(value = "获取用户列表")
    public String getUserList(HttpServletRequest request,
                              @RequestBody User user) {
        Cookie[] cookies = request.getCookies();
        User result = new User();
        // 验证Cookies和用户信息
        if (!Objects.isNull(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login") && cookie.getValue().equals("true")
                        && user.getUsername().equals("rex") && user.getPassword().equals("123456")) {
                    result.setNickname("ADMIN");
                    result.setAge(25);
                    result.setSex("MALE");
                    return result.toString();
                }
            }
        }
        return "参数不合法";
    }
}
