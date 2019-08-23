package com.atguigu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    /**
     * 配置一个处理abc请求的控制器
     * @param model
     * @return
     */
    @GetMapping("/abc")
    public String hello (Model model) {
        model.addAttribute("msg","你好");
        return "success";
    }
}
