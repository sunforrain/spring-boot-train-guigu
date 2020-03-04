package com.atguigu.springboot.config;

import com.atguigu.springboot.component.LoginHandlerInterceptor;
import com.atguigu.springboot.component.MyLocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

// 视频33 尚硅谷_SpringBoot_web开发-扩展与全面接管SpringMVC
//使用WebMvcConfigurer,不放开@EnableWebMvc可以来扩展SpringMVC的功能
// 放开@EnableWebMvc则全面接管,springMVC的自动配置全部失效
@Configuration

//@EnableWebMvc  //不要接管springMvc
public class MyMvcConfig implements WebMvcConfigurer {

    // 视频33 尚硅谷_SpringBoot_web开发-扩展与全面接管SpringMVC
    // 添加一个视图映射
    // 这里只是增加,不会影响默认的视图映射配置
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
        //浏览器发送 /atguigu 请求来到 success
        registry.addViewController("/atguigu").setViewName("success");
        // 视频34 尚硅谷_SpringBoot_web开发-【实验】-引入资源
        // 2.1.5中WebMvcConfigurerAdapter已过期,不建议使用下面的方法,直接Override WebMvcConfigurer相关方法就好
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        // 视频36 尚硅谷_SpringBoot_web开发-【实验】-登陆&拦截器
        // 定义一个视图映射,参考LoginController,用于登陆后重定向到main.html,对应dashboard.html,这样还隐藏了真正的页面路径
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    // 视频36 尚硅谷_SpringBoot_web开发-【实验】-登陆&拦截器
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //静态资源； *.css , *.js
        //SpringBoot已经做好了静态资源映射,所以不用配置静态资源的
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")// 配置需要拦截的请求,/**标示任意层级的任意请求
                .excludePathPatterns("/index.html","/","/user/login");// 配置不需要拦截的请求,就是登录页面
    }

    /**
     *  视频34 尚硅谷_SpringBoot_web开发-【实验】-引入资源
     *  所有的WebMvcConfigurerAdapter都会一起起作用
     *  只适用springBoot 1版本, WebMvcConfigurerAdapter已经过期
     *  可以用WebMvcConfigurationSupport替换,但是会导致springMVC的默认配置失效,
     *  直接Override WebMvcConfigurer相关方法就好
     */
//    @Bean // 将组件注册在容器中
//    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
//        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
//            // 注册自定义视图解析器
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/").setViewName("login");
//                registry.addViewController("/index.html").setViewName("login");
//                // 自定义视图解析器,登陆后重定向到main.html,对应dashboard.html,这样还隐藏了真正的页面路径
//                registry.addViewController("/main.html").setViewName("dashboard");
//            }
//
////            // 注册拦截器 为了测试springboot的错误处理,屏蔽
////            @Override
////            public void addInterceptors(InterceptorRegistry registry) {
////                //静态资源； *.css , *.js
////                //SpringBoot已经做好了静态资源映射,所以不用配置静态资源的
////                registry.addInterceptor(new LoginHandlerInterceptor())
////                        .addPathPatterns("/**")// 配置需要拦截的请求,/**标示任意层级的任意请求
////                        .excludePathPatterns("/index.html","/","/user/login");// 配置不需要拦截的请求,就是登录页面
////            }
//        };
//        return adapter;
//    }

    // 视频35 尚硅谷_SpringBoot_web开发-【实验】-国际化
    // 配置上自定义的区域信息解析器,效果是链接的参数上有参数就用自定义的,没参数就用springBoot默认的
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }
}
