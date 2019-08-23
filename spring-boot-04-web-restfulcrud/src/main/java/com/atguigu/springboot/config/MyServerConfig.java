package com.atguigu.springboot.config;

import com.atguigu.springboot.filter.MyFilter;
import com.atguigu.springboot.listener.MyListener;
import com.atguigu.springboot.servlet.MyServlet;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRegistration;
import java.util.Arrays;

@Configuration
public class MyServerConfig {

    // 注册三大组件
    // 注册servlet
    @Bean //一定要将这个定制器加入到容器中
    public ServletRegistrationBean myServlet() {
        // 使用ServletRegistrationBean的第二个构造器,参数一就是原生的Servlet,参数二是映射的链接
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet(),"/myServlet");
        return servletRegistrationBean;
    }
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
    // 注册listener
    @Bean
    public ServletListenerRegistrationBean myListener () {
        ServletListenerRegistrationBean<MyListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<MyListener>(new MyListener());
        return servletListenerRegistrationBean;
    }


    // 嵌入式的Servlet容器的定制器,可以修改servlet容器的配置
    @Bean //一定要将这个定制器加入到容器中
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
        return new EmbeddedServletContainerCustomizer() {
            //定制嵌入式的Servlet容器相关的规则
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.setPort(8083);
            }
        };
    }
}
