package com.atguigu.springboot.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 视频36 尚硅谷_SpringBoot_web开发-【实验】-登陆&拦截器
 * 登录检查,没有登录的用户不能进行数据的增删改查操作,这样不会存在直接访问dashboard也能进去的情况
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    // 目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if ( user == null) {
            // 未登录,返回登录页面
            request.setAttribute("msg","没有权限,请先登录");
            // 调用转发器,转发request和response请求
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        } else {
            // 已登录,放行请求
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
