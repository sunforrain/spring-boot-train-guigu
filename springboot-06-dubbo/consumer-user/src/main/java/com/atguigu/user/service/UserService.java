package com.atguigu.user.service;


import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import com.atguigu.ticket.service.TicketService;
@Service
public class UserService {
    // 视频33 分布式-SpringBoot、Dubbo、Zookeeper整合
    // @Reference 可以引用服务,引用的规则是同样的全类名
    @Reference(version = "1.0.0")
    TicketService ticketService;

    public void hello(){
        String tickets = ticketService.getTicket();
        System.out.println("买到票了"+tickets );
    }
}