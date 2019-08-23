package com.atguigu.springboot;

import com.atguigu.springboot.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.context.ApplicationContext;

/**
 * @SpringBootTest spring boot 单元测试
 * 测试驱动器是SpringRunner, 而不是原来的jUnit
 * 可以在测试期间很方便的类似编码一样进行自动注入等容器的功能
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot02ConfigApplicationTests {
    @Autowired
    Person person;
    // 注入spring的ioc容器
    @Autowired
    ApplicationContext ioc;

    /**
     * 测试在ioc容器中有没有配置beans.xml中配置的HelloService
     * 在控制台打印结果
     * 结果是Spring Boot里面没有Spring的配置文件，我们自己编写的配置文件，也不能自动识别
     * 在主运行类SpringBoot02ConfigApplication中配置了@ImportResource(locations = "classpath:beans.xml")才行
     */
    @Test
    public void testHelloService () {
        // name对应的id
        boolean b = ioc.containsBean("helloService02");
        System.out.println(b);
    }
    @Test
    public void contextLoads() {
        System.out.println(person);
    }

}
