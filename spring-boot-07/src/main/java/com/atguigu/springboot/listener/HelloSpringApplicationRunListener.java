package com.atguigu.springboot.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

// 视频70 SpringBoot_原理-事件监听机制相关测试
public class HelloSpringApplicationRunListener implements SpringApplicationRunListener {
    // 参考EventPublishingRunListener,要有一个有参构造器,否则项目会报错
    public HelloSpringApplicationRunListener (SpringApplication application, String[] args){

    }
    // 监听启动调用的方法
    @Override
    public void starting() {
        System.out.println("SpringApplicationRunListener...starting...");
    }
    // 环境准备好调用的方法
    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        // 可以获得系统信息等
        Object o = environment.getSystemProperties().get("os.name");
        System.out.println("SpringApplicationRunListener...environmentPrepared.."+o);
    }

    // ioc容器准备好了,ioc容器的对象也传入进来了
    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener...contextPrepared...");
    }

    // ioc容器加载完成,ioc容器的对象也传入进来了
    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener...contextLoaded...");
    }

    // ioc容器refresh完成,但是还没调用CommandLineRunners和CommandLineRunners
    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener...started...");
    }

    // 就是以前的finished方法,run()运行完成就会调用
    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener...running...");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }
}
