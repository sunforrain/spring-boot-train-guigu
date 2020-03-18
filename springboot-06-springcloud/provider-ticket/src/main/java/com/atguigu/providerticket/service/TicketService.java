package com.atguigu.providerticket.service;

import org.springframework.stereotype.Service;

/**
 * 视频35 分布式-服务注册
 * 将服务发布出去
 */
@Service
public class TicketService {

    public String getTicket(){
        System.out.println("8002");
        return "《厉害了，我的国》";
    }
}
