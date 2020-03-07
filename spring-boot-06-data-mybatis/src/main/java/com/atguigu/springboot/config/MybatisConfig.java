package com.atguigu.springboot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 视频64 尚硅谷_SpringBoot_数据访问-整合MyBatis（二）-注解版MyBatis
// 自定义的mybatis的配置类
// @MapperScan 使用MapperScan批量扫描所有的Mapper接口,就不用每个Mapper文件都加 @Mapper 了
@MapperScan(value = "com.atguigu.springboot.mapper")
@Configuration
public class MybatisConfig {
    // 定制一个mybatis的定制器
    // 这里的原理和之前配置servlet容器的定制器是很类似的都是实现一个xxxCustomizer接口的customize方法
    // 参考MybatisAutoConfiguration =>
    //  sqlSessionFactory方法,里面的applyConfiguration方法有遍历ConfigurationCustomizer的逻辑
    @Bean
    public ConfigurationCustomizer configurationCustomizer () {
        return new ConfigurationCustomizer(){
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                // 开启驼峰命名法的映射规则
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
