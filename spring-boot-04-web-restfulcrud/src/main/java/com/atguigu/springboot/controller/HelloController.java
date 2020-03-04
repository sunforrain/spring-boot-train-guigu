package com.atguigu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

    //    @RequestMapping({"/","/login.html"})
//    public String index(){
//        return "index";
//    }
    // 视频29 尚硅谷_SpringBoot_web开发-webjars&静态资源映射规则
    @ResponseBody
    @RequestMapping("/hello")
    public String hello () {
//        if(user.equals("aaa")){
////            throw new UserNotExistException();
////        }
        return "hello world";
    }

    @ResponseBody
    @RequestMapping("/hello2")
    public String hello2 (@RequestParam("user")String user) {
        if(user.equals("aaa")){
//            throw new UserNotExistException();
        }
        return "hello world";
    }

    // 视频31 尚硅谷_SpringBoot_web开发-thymeleaf语法
    // 查出一些数据,在页面展示
    @RequestMapping("/success")
    public String success (Map<String, Object> map) {
        // classpath:/templates/success.html
        map.put("hello","你好");
        return "success";
    }

//    @RequestMapping("/success")
//    public String success2 (Map<String, Object> map) {
//        // classpath:/templates/success.html
//        map.put("hello","<h1>你好</h1>");
//        map.put("users", Arrays.asList("zhangsan", "lisi", "wangwu"));
//        return "success";
//    }
}
