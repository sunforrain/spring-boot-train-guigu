package com.atguigu.springboot.controller;

import com.atguigu.springboot.exception.UserNotExistException;
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
        return "hello world";
    }

    // 视频45 尚硅谷_SpringBoot_web开发-定制错误数据
    // 简单测试向前台弹错的情况,要带参数user=aaa就会弹异常,如果不带参数则会报缺少参数错误 Required String parameter 'user' is not present
    @ResponseBody
    @RequestMapping("/hello2")
    public String hello2 (@RequestParam("user")String user) {
        if(user.equals("aaa")){
            throw new UserNotExistException();
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

    // 视频31 尚硅谷_SpringBoot_web开发-thymeleaf语法
    // 演示转义和不转义的对比和遍历标签
    @RequestMapping("/success2")
    public String success2 (Map<String, Object> map) {
        // classpath:/templates/success.html
        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan", "lisi", "wangwu"));
        return "success";
    }
}
