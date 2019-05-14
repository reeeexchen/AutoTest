package top.rex.code.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.rex.code.model.User;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/11 0:16
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    // 获取执行SQL的对象
    @Autowired
    private SqlSessionTemplate template;

    @GetMapping("/count")
    @ApiOperation(value = "获取用户数")
    public Integer getUserCount() {
        return template.selectOne("getUserCount");
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增用户")
    public int addUser(@RequestBody User user) {
        return template.insert("addUser", user);
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改用户信息")
    public int updateUser(@RequestBody User user) {
        return template.update("updateUser", user);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除用户")
    public int deleteUser(@RequestParam Integer id) {
        return template.delete("deleteUser", id);
    }
}
