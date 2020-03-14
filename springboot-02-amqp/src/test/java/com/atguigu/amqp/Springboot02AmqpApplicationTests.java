package com.atguigu.amqp;

import com.atguigu.amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02AmqpApplicationTests {

    // 自动配置类都注入的,可以直接拿来用
    @Autowired
    RabbitTemplate rabbitTemplate;

    // 视频19 消息-AmqpAdmin管理组件的使用
    @Autowired
    AmqpAdmin amqpAdmin;

    // 视频19 消息-AmqpAdmin管理组件的使用
    @Test
    public void createExchange(){

        // 创建交换器
		amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
		System.out.println("创建完成");

		// 创建队列
		amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));

		// 创建绑定规则
		amqpAdmin.declareBinding(
		        new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE,
                        "amqpadmin.exchange","amqp.haha",null));

        //amqpAdmin.declarexxxx都是用来创建组件的
    }

    /**
     * 视频17 消息-RabbitTemplate发送接受消息&序列化机制
     * 1、单播（点对点）
     */
    @Test
    public void contextLoads() {
        //Message需要自己构造一个;定义消息体内容和消息头
        //rabbitTemplate.send(exchage,routeKey,message);

        //object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq；
        // exchage 交换器, routeKey 路由件, object 消息
        //rabbitTemplate.convertAndSend(exchage,routeKey,object);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("data", Arrays.asList("helloworld",123,true));
        // 对象被默认序列化以后发送出去,这里默认是SimpleMessageConverter
//        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",map);
        // 有了转换器(MyAMQPConfig中自定义),对象才能够发送,默认的不能发对象
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("西游记","吴承恩"));

    }

    // 视频17 消息-RabbitTemplate发送接受消息&序列化机制
    //接受数据,如何将数据自动的转为json发送出去
    @Test
    public void receive(){
        // 注意这个方法只能拿到第一条消息,收到后这条消息就自动在mq中清除,如果mq的队列中没消息,会报空指针
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }
    /**
     * 视频17 消息-RabbitTemplate发送接受消息&序列化机制
     * 广播
     */
    @Test
    public void sendMsg(){
        // 因为是广播,就不用指定路由件了
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("红楼梦","曹雪芹"));
    }
}
