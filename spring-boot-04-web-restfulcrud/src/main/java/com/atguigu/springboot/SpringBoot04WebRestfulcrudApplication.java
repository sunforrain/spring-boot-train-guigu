package com.atguigu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * 视频29 尚硅谷_SpringBoot_web开发-webjars&静态资源映射规则
 *     有关mvc的可配置项可见WebMvcAutoConfiguration.java和WebMvcProperties.java
 *     静态资源映射见 addResourceHandlers()方法
 *     首页映射见 welcomePageHandlerMapping()方法
 *     配置喜欢的图标见 FaviconConfiguration()方法
 *
 *  视频30 尚硅谷_SpringBoot_web开发-引入thymeleaf
 *      因为springBoot打包为jar,内置tomcat的特性,导致无法使用jsp,需要使用其他的模板引擎
 *      springBoot推荐thymeleaf
 */

@SpringBootApplication
public class SpringBoot04WebRestfulcrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot04WebRestfulcrudApplication.class, args);
    }
//    @Bean
//    public ViewResolver myViewResolver() {
//        return new myViewResolver();
//    }
//
//    private static class myViewResolver implements ViewResolver {
//
//        @Override
//        public View resolveViewName(String viewName, Locale locale) throws Exception {
//            return null;
//        }
//    }
}
