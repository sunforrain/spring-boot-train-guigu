package com.atguigu.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot06DataJdbcApplicationTests {
    @Autowired
    DataSource dataSource;

    // 视频61 尚硅谷_SpringBoot_数据访问-JDBC&自动配置原理,测试连接下数据源
    @Test
    public void contextLoads() throws SQLException {
        Connection connection = dataSource.getConnection();
        // 打印默认的数据源,默认是用org.apache.tomcat.jdbc.pool.DataSource
        System.out.println(connection);
    }

}
