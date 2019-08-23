package com.atguigu.springboot.component;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

// 给容器中加入我们自定义的ErrorAttributes
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    // 重写父类的方法,获取到要传出去的ErrorAttributes
    // 返回值的map就是页面和json能获取的所有字段
    // RequestAttributes就是封装了request对象的
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String,Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);
        map.put("company:","atguigu");
        // 注意看方法说明,0是request域中,1是session域等
        // 获得我们异常处理器携带的数据
        Map<String,Object> ext = (Map<String,Object>)requestAttributes.getAttribute("ext",0);
        map.put("ext",ext);
        return map;
    }
}
