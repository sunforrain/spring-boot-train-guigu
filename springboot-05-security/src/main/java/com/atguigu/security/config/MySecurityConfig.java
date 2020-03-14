package com.atguigu.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 继承WebSecurityConfigurerAdapter是必须的,@EnableWebSecurity也是
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 视频28 安全-登录&认证&授权
        // 父类有默认的授权规则,因而注掉
        //super.configure(http);
        //定制请求的授权规则, antMatchers定制匹配的路径
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1") // 没有指定角色不能进指定路径
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        //开启自动配置的登陆功能，效果，如果没有登陆，没有权限就会来到登陆页面,默认是/login
//        http.formLogin();

        // 视频30 安全-记住我&定制登陆页
        http.formLogin().usernameParameter("user").passwordParameter("pwd")
                .loginPage("/userlogin");
        //1、/login来到登陆页
        //2、重定向到/login?error表示登陆失败
        //3、更多详细规定
        //4、默认post形式的 /login代表处理登陆
        //5、一但定制loginPage；那么 loginPage的post请求就是登陆,登陆不能用默认的/login

        // 视频29 安全-权限控制&注销
        //开启自动配置的注销功能。
        http.logout().logoutSuccessUrl("/");//注销成功以后来到首页
        //1、访问 /logout 表示用户注销，清空session
        //2、注销成功会返回 /login?logout 页面,可以用logoutSuccessUrl改注销后页面；

        // 视频30 安全-记住我&定制登陆页
        //开启记住我功能
        http.rememberMe().rememberMeParameter("remember");
        //登陆成功以后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登录
        //点击注销会删除cookie

    }

    // 视频28 安全-登录&认证&授权
    //定义认证规则,定义用户信息
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        // 正常这里应该连接数据库,演示就算了
        // Spring security 5.0中新增了多种加密方式，也改变了密码的格式
        // 前面的id是加密方式，id可以是bcrypt、sha256等，后面跟着的是加密后的密码。
        // 也就是说，程序拿到传过来的密码的时候，会首先查找被“{”和“}”包括起来的id，来确定后面的密码是被怎么样加密的，
        // 如果找不到就认为id是null,报错There is no PasswordEncoder mapped for the id "null"
        // 老版本写法,报错
//        auth.inMemoryAuthentication().withUser("zhangsan").password("123456").roles("VIP1","VIP2");
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("1").password(new BCryptPasswordEncoder().encode("123")).roles("VIP1","VIP2")
                .and()
                .withUser("2").password(new BCryptPasswordEncoder().encode("123")).roles("VIP2","VIP3")
                .and()
                .withUser("3").password(new BCryptPasswordEncoder().encode("123")).roles("VIP2","VIP3");

    }
}
