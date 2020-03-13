package com.atguigu.amqp.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 视频17 消息-RabbitTemplate发送接受消息&序列化机制
 */
@Configuration
public class MyAMQPConfig {

    // 换成转为json的转换器,会被RabbitAutoConfiguration 自动加到转换器中
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
