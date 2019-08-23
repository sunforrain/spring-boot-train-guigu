package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Controller 控制器用,处理请求
 */
@Controller
public class HelloController {
    // @RequestMapping 接受浏览器的/hello请求
    // @ResponseBody 表示一个返回方法,返回浏览器"Hello World"
    @RequestMapping("/hello")
    @ResponseBody
    public String hello () {
        return "Hello World";
    }
}
