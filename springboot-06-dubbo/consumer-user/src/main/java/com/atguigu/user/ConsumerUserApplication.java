package com.atguigu.user;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 视频33 分布式-SpringBoot、Dubbo、Zookeeper整合
 * 1、引入依赖‘
 * 2、配置dubbo的注册中心地址
 * 3、引用服务
 * 注意@EnableDubbo和@DubboComponentScan都要有,在配置文件里面设置扫描包不好用,消费者找不到提供者的service
 */
@EnableDubbo
@DubboComponentScan
@SpringBootApplication
public class ConsumerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerUserApplication.class, args);
    }

}
