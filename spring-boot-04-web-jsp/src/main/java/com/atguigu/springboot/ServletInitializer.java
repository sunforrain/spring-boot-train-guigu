package com.atguigu.springboot;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 视频51 尚硅谷_SpringBoot_web开发-使用外部Servlet容器&JSP支持
 * 必须编写一个SpringBootServletInitializer的子类，并调用configure方法
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // 传入SpringBoot应用的主程序
        return application.sources(SpringBoot04WebJspApplication.class);
    }

}
