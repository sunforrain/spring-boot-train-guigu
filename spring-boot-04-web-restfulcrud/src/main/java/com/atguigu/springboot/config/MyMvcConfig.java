package com.atguigu.springboot.config;

import com.atguigu.springboot.component.LoginHandlerInterceptor;
import com.atguigu.springboot.component.MyLocalResolver;
import com.sun.corba.se.spi.resolver.LocalResolver;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
@Configuration
//@EnableWebMvc  不要接管springMvc
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    // 添加一个视图映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
        //浏览器发送 /atguigu 请求来到 success
        registry.addViewController("/atguigu").setViewName("success");
    }

    // 所有的WebMvcConfigurerAdapter都会一起起作用
    @Bean // 将组件注册在容器中
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            // 注册自定义视图解析器
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                // 自定义视图解析器,登陆后重定向到main.html,对应dashboard.html,这样还隐藏了真正的页面路径
                registry.addViewController("/main.html").setViewName("dashboard");
            }
//            // 注册拦截器 为了测试springboot的错误处理,屏蔽
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                //静态资源； *.css , *.js
//                //SpringBoot已经做好了静态资源映射,所以不用配置静态资源的
//                // 注意2.0需要配置,会被拦截!
//                registry.addInterceptor(new LoginHandlerInterceptor())
//                        .addPathPatterns("/**")// 配置需要拦截的请求,/**标示任意层级的任意请求
//                        .excludePathPatterns("/index.html","/","/user/login");// 配置不需要拦截的请求,就是登录页面
//            }
        };


        return adapter;
    }
    // 配置上自定义的区域信息解析器
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }
}
