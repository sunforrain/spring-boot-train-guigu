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
    // 视频36 尚硅谷_SpringBoot_web开发-【实验】-登陆&拦截器
    // 定义登陆请求的映射
    @PostMapping(value = "/user/login")
    public String login (@RequestParam("username") String username,
                         @RequestParam("password")String password,
                         Map<String,Object> map, HttpSession session) {
        if (!StringUtils.isEmpty(username) && "123456".equals(password)){// 登陆成功
            // 在session中保存用户信息,用于拦截器中的用户登录检查
            session.setAttribute("loginUser", username);
            // 有模板引擎的解析不用写前面的路径和后面的html
            // 登陆成功,防止表单重复提交,可以重定向到主页
            return "redirect:/main.html";
        }else {// 登录失败
            map.put("msg","用户名密码错误");
            return "login";
        }

    }
}
