package com.atguigu.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 用来绑定相关配置的属性类,与自动配置类配合
 */
@ConfigurationProperties(prefix = "atguigu.hello") // 绑定配置文件中以atguigu.hello开头的配置
public class HelloProperties {
    private String prefix;
    private String suffix;
    public String getPrefix() {
        return prefix;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    public String getSuffix() {
        return suffix;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
