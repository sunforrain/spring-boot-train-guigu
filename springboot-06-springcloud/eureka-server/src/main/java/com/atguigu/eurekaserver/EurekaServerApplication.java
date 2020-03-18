package com.atguigu.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 视频34 分布式-SpringCloud-Eureka注册中心
 * 注册中心
 * 1、配置Eureka信息
 * 2、@EnableEurekaServer
 * /localhost:8761 可以访问注册中心
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
