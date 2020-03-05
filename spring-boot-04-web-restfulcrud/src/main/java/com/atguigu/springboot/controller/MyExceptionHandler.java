package com.atguigu.springboot.controller;

import com.atguigu.springboot.exception.UserNotExistException;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 视频45 尚硅谷_SpringBoot_web开发-定制错误数据
 * 异常处理器,专门处理异常
 * 在springMVC中要成为异常处理器,必须要有@ControllerAdvice
 */
@ControllerAdvice
public class MyExceptionHandler {
    // 异常处理1,但不是自适应效果,html和app访问都只返回json了
    // 要返回json数据,需要用到@ResponseBody注解
    // @ExceptionHandler内可以指定异常,也可以指定所有异常
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String,Object> handleException(Exception e){
//        Map<String,Object> map = new HashMap<>();
//        map.put("code","user.notexist");
//        map.put("message",e.getMessage());
//        return map;
//    }

    // 异常处理2,报异常后转发给/error请求,利用BasicErrorController能自适应处理错误的特点
    // 但是不给自定义异常指定错误码,会回到默认的错误页面,且错误码是200
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        /*
         * 错误码的获得见: org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.
         * errorHtml =>
         * AbstractErrorController.getStatus() 其中有:
         * Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
         * 我们可以用set的方式设置自定义异常的错误码
         */
        // 传入我们自己的状态码,且不会影响其他默认错误码
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexist");
        map.put("message",e.getMessage());
        request.setAttribute("ext",map);
        // 转发到/error
        return "forward:/error";
    }
}
