package com.atguigu.springboot.config;

import com.atguigu.springboot.filter.MyFilter;
import com.atguigu.springboot.listener.MyListener;
import com.atguigu.springboot.servlet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MyServerConfig {

    // 注册三大组件
    // 视频47 尚硅谷_SpringBoot_web开发-注册servlet三大组件
    // http://localhost:8083/crud/myServlet
    // 注册servlet
    @Bean //一定要将这个定制器加入到容器中
    public ServletRegistrationBean myServlet() {
        // 使用ServletRegistrationBean的第二个构造器,参数一就是原生的Servlet,参数二是映射的链接
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet(),"/myServlet");
        return servletRegistrationBean;
    }

    // 视频47 尚硅谷_SpringBoot_web开发-注册servlet三大组件
    // 注册filter
    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        // 分段设置filter的设置
        // 设置我们自己的filter
        filterRegistrationBean.setFilter(new MyFilter());
        // 设置拦截的请求路径,可见传入的是一个Collection<String>
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return filterRegistrationBean;
    }

    // 视频47 尚硅谷_SpringBoot_web开发-注册servlet三大组件
    // 注册listener
    @Bean
    public ServletListenerRegistrationBean myListener () {
        ServletListenerRegistrationBean<MyListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<MyListener>(new MyListener());
        return servletListenerRegistrationBean;
    }

    // 视频46 尚硅谷_SpringBoot_web开发-嵌入式Servlet容器配置修改
    // 嵌入式的Servlet容器的定制器,可以修改servlet容器的配置
    @Bean //一定要将这个定制器加入到容器中
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> embeddedServletContainerCustomizer(){
        // 在Spring Boot2.0以上配置嵌入式Servlet容器时EmbeddedServletContainerCustomizer类不存在，
        // 被WebServerFactoryCustomizer替代
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            //定制嵌入式的Servlet容器相关的规则
            @Override
            public void customize(ConfigurableWebServerFactory container) {
                container.setPort(8083);
            }
        };
    }
}
