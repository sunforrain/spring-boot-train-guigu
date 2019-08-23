package com.atguigu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
*  @SpringBootApplication 来标注一个主程序类 ，说明这是一个Spring Boot应用
*/
@SpringBootApplication
public class HelloWorldMainApplication {
    // IDEA的main方法缩写psvm
    // 直接运行main方法就能启动这个程序,而不再是要配tomcat
    public static void main(String[] args) {
        // spring应用启动起来
        SpringApplication.run(HelloWorldMainApplication.class, args);
    }
}
