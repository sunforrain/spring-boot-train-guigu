package com.atguigu.springboot.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 视频35 尚硅谷_SpringBoot_web开发-【实验】-国际化
 * 可以在链接上携带区域信息
 */
public class MyLocalResolver implements LocaleResolver {
    /**
     * 解析区域信息的方法
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("l");
        // 获取到系统默认的区域语言解析器,如果没有获取到链接中参数就用默认的,否则在下面用自己的
        Locale locale = Locale.getDefault();
        // 这里用到了springBoot的字符串工具类,判断参数是否为空
        if (!StringUtils.isEmpty(l)){
            String[] split = l.split("_");
            // 构造器有两个参数,第一个是语言代码,第二个是国家代码
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
