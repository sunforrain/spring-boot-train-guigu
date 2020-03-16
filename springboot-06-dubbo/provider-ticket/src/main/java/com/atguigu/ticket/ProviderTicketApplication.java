package com.atguigu.ticket;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 视频33 分布式-SpringBoot、Dubbo、Zookeeper整合
 * 		1、将服务提供者注册到注册中心,见配置文件和service类
 * 	    1、引入dubbo和zkclient相关依赖
 * 	    2、配置dubbo的扫描包和注册中心地址
 * 	    3、使用@Service发布服务
 * 	    注意@EnableDubbo和@DubboComponentScan都要有,在配置文件里面设置扫描包不好用,消费者找不到提供者的service
 */
@EnableDubbo
@DubboComponentScan
@SpringBootApplication
public class ProviderTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderTicketApplication.class, args);
	}

}
