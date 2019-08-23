package com.atguigu.springboot.controller;

import com.atguigu.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理器,专门处理异常
 * 在springMVC中要成为异常处理器,必须要有@ControllerAdvice
 */
@ControllerAdvice
public class MyExceptionHandler {
    // 异常处理1,有缺陷,不是自适应效果,html和app都成了json了
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

    // 异常处理2,报异常后转发给/error请求
    // 但是不指定错误码,会回到默认的错误页面,且错误码是200
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        // 传入我们自己的状态码
//     Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexist");
        map.put("message",e.getMessage());
        request.setAttribute("ext",map);
        // 转发到/error
        return "forward:/error";
    }
}
