package com.atguigu.springboot.component;


import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

// 视频45 尚硅谷_SpringBoot_web开发-定制错误数据
// 给容器中加入我们自定义的ErrorAttributes
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    // 重写父类的方法,获取到要传出去的ErrorAttributes
    // 返回值的map就是页面和json能获取的所有字段
    // RequestAttributes就是封装了request对象的
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        // 调用父类的方法可以获取到默认的错误信息
        Map<String,Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("company","atguigu");
        // 获得我们异常处理器携带的数据
        // 注意看方法说明,0是request域中,1是session域等
        Map<String,Object> ext = (Map<String,Object>)webRequest.getAttribute("ext",0);
        map.put("ext",ext);
        return map;
    }
    // 最终的效果：响应是自适应的，可以通过定制ErrorAttributes改变需要返回的内容
}
