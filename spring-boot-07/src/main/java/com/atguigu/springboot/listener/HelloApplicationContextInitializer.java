package com.atguigu.springboot.listener;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

// 视频70 SpringBoot_原理-事件监听机制相关测试
// ConfigurableApplicationContext是用来初始化IOC容器的,所有的Initializer都要实现ApplicationContextInitializer接口
public class HelloApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        // 可以打印一下ioc容器
        System.out.println("ApplicationContextInitializer...initialize..."+ configurableApplicationContext);
    }
}
