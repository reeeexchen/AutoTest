package top.rex.code.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/9 21:49
 * @Version: 1.0
 */
@RestController
@Api(value = "/",description = "TestGetController")
public class TestGetController {
    @GetMapping("/getCookies")
    @ApiOperation(value = "获取Cookies")
    public String getCookies(HttpServletResponse response) {
        // HttpServletResponse 装响应信息
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "获取Cookies信息成功";
    }

    // 要求Client携带Cookie访问
    @GetMapping("/getWithCookies")
    @ApiOperation(value = "携带Cookie访问")
    public String getWithCookies(HttpServletRequest request) {
        // HttpServletRequest 装请求信息
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "必须携带Cookies请求";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                return "携带Cookies请求访问成功";
            }
        }
        return "必须携带Cookies请求";
    }

    // 1.带参数的GET请求 key=value&key=value
    // 模拟获取商品列表,传入开始位置，结束位置
    @GetMapping("/getWithParam")
    @ApiOperation(value = "带参数的GET请求,key=value")
    public Map<String, Integer> GetList(@RequestParam Integer start, @RequestParam Integer end) {
        Map<String, Integer> shopList = new HashMap<>();
        shopList.put("鞋子", 400);
        shopList.put("干脆面", 4);
        shopList.put("衬衫", 300);
        shopList.put("START", start);
        shopList.put("END", end);
        return shopList;
    }

    // 2.带参数的GET请求 /1/2
    @GetMapping("/getWithParam/{start}/{end}")
    @ApiOperation(value = "带参数的GET请求,/path")
    public Map<String, Integer> GetList2(@PathVariable(name = "start") Integer start, @PathVariable(name = "end") Integer end) {
        Map<String, Integer> shopList = new HashMap<>();
        shopList.put("鞋子", 400);
        shopList.put("干脆面", 4);
        shopList.put("衬衫", 300);
        shopList.put("START", start);
        shopList.put("END", end);
        return shopList;
    }


}
