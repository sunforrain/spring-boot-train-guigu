package com.atguigu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
//    @PostMapping 是rest风格的注解 包括了post的requestMapping,还有
//    @DeleteMapping
//    @PutMapping
//    @GetMapping
    @PostMapping(value = "/user/login")
    public String login (@RequestParam("username") String username,
                         @RequestParam("password")String password,
                         Map<String,Object> map, HttpSession session) {
        if (!StringUtils.isEmpty(username) && "123456".equals(password)){// 登陆成功
            // 有模板引擎的解析不用写前面的路径和后面的html
            // 登陆成功,防止表单重复提交,可以重定向到主页
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        }else {// 登录失败
            map.put("msg","用户名密码错误");
            return "login";
        }

    }
}
