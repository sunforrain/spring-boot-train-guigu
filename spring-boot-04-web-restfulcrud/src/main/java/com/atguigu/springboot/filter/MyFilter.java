package com.atguigu.springboot.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 视频47 尚硅谷_SpringBoot_web开发-注册servlet三大组件
 * 自己编写的filter组件类
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Myfilter process...");
        // 没什么功能,直接放行
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
