package com.atguigu.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 视频62 尚硅谷_SpringBoot_数据访问-整合Druid&配置数据源监控
// 自定义的druid数据源配置类
// 以便能够绑定druid的配置属性
@Configuration
public class DruidConfig {
    // 绑定配置信息,之前druid相关的配置在spring.datasource前缀的属性中没有,通过@ConfigurationProperties绑定
    @ConfigurationProperties(prefix= "spring.datasource")
    @Bean
    public DataSource druid () {
        return new DruidDataSource();
    }

    // 配置druid的监控
    // 1 配置一个管理后台的Servlet
    // ServletRegistrationBean是servlet三大组件部分讲的
    @Bean
    public ServletRegistrationBean statViewServlet () {
        // /druid/* 最后的/*不能少
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        // 配置servlet的启动参数
        Map<String,String> initParams = new HashMap<>();
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
//        initParams.put("allow","localhost");// 只允许本地访问
        initParams.put("allow","");// 默认就是允许所有访问
        initParams.put("deny","192.168.15.21");
        bean.setInitParameters(initParams);
        return bean;
    }
    // 2 配置一个监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter () {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<>();
        // 其他可设置参数到WebStatFilter里看
        // 不拦截的请求
        initParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
