package com.atguigu.ticket.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

// 视频33 分布式-SpringBoot、Dubbo、Zookeeper整合
@Component
@Service(version = "1.0.0") //将服务发布出去,是dubbo包下的注解,不是spring的
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "《厉害了，我的国》";
    }
}
