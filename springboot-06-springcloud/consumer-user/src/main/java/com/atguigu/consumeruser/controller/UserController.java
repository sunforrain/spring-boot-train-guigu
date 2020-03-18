package com.atguigu.consumeruser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 视频36 分布式-服务发现&消费
 *
 */
@RestController
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    // 如: http://192.168.1.17:8200/buy?name=张三  ip参考本机的ip
    @GetMapping("/buy")
    public String buyTicket(String name){
        // 调用服务提供者在eureka注册的名字和地址,返回的已知是个字符串,直接转
        String s = restTemplate.getForObject("http://PROVIDER-TICKET/ticket", String.class);
        return name+"购买了"+s;
    }
}
