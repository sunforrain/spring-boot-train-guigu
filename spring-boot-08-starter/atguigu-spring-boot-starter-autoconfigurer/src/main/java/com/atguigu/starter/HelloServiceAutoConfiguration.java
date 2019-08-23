package com.atguigu.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 标注这是一个配置类
@ConditionalOnWebApplication // web应用才生效
@EnableConfigurationProperties(HelloProperties.class) // 使属性类生效,否则无法Autowired
public class HelloServiceAutoConfiguration {
    @Autowired
    HelloProperties helloProperties;
    // 给容器中增加一个HelloService,可以让别人引用
    @Bean
    public HelloService helloService(){
        HelloService service = new HelloService();
        service.setHelloProperties(helloProperties);
        return service;
    }
}
