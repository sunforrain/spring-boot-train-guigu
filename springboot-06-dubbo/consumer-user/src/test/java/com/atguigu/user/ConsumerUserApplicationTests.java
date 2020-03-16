package com.atguigu.user;

import com.atguigu.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerUserApplicationTests {

    @Autowired
    UserService userService;

    // 视频33 分布式-SpringBoot、Dubbo、Zookeeper整合
    // 在测试时,提供者的服务一定要开启着
    @Test
    public void contextLoads() {
        userService.hello();
    }

}
