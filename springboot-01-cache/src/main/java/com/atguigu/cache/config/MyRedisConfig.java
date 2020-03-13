package com.atguigu.cache.config;


import com.atguigu.cache.bean.Department;
import com.atguigu.cache.bean.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.net.UnknownHostException;
import java.time.Duration;

@Configuration
public class MyRedisConfig {

    // 以下是springBoot 1.xx的配置方式,2版本引入Lettuce后序列化不一定这么用了
    // 视频11 缓存-RedisTemplate&序列化机制
    // 自定义一个Employee的序列化机制,使保存对象时值是个json,而不是序列化值
    @Bean
    public RedisTemplate<Object, Employee> empRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Employee> template = new RedisTemplate<Object, Employee>();
        template.setConnectionFactory(redisConnectionFactory);
        // 使用Jackson2JsonRedisSerializer作为序列化器
        Jackson2JsonRedisSerializer<Employee> ser = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        template.setDefaultSerializer(ser);
        return template;
    }

    // 视频12 缓存-自定义CacheManager
    // 自定义一个Department的序列化机制,使保存对象时值是个json,而不是序列化值
    @Bean
    public RedisTemplate<Object, Department> deptRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Department> template = new RedisTemplate<Object, Department>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Department> ser = new Jackson2JsonRedisSerializer<Department>(Department.class);
        template.setDefaultSerializer(ser);
        return template;
    }

//    // 以下是springBoot 1.xx的配置方式,2版本会报错
//    // 视频12 缓存-自定义CacheManager
//    //CacheManagerCustomizers可以来定制缓存的一些规则
//    @Primary  //将某个缓存管理器作为默认的
//    @Bean
//    public RedisCacheManager employeeCacheManager(RedisTemplate<Object, Employee> empRedisTemplate){
//        RedisCacheManager cacheManager = new RedisCacheManager(empRedisTemplate);
//        //key多了一个前缀
//
//        //使用前缀，默认会将CacheName作为key的前缀
//        cacheManager.setUsePrefix(true);
//
//        return cacheManager;
//    }
//
//    // 视频12 缓存-自定义CacheManager
//    //  自定义和Department相关的CacheManager
//    @Bean
//    public RedisCacheManager deptCacheManager(RedisTemplate<Object, Department> deptRedisTemplate){
//        RedisCacheManager cacheManager = new RedisCacheManager(deptRedisTemplate);
//        //key多了一个前缀
//
//        //使用前缀，默认会将CacheName作为key的前缀
//        cacheManager.setUsePrefix(true);
//
//        return cacheManager;
//    }

    // 视频12 缓存-自定义CacheManager
    // springBoot 2.x.x 的配置方式
    // org.springframework.data.redis.serializer 里面全是序列化器
    // key键序列化方式
    private RedisSerializer<String> keySerializer() {
        // 储存规则类似于dept::1,两层文件夹,dept/文件夹/value
        return new StringRedisSerializer();
    }

    // value值序列化方式
    private GenericJackson2JsonRedisSerializer valueSerializer(){
        return new GenericJackson2JsonRedisSerializer();
    }

    /*
     * 配置缓存管理器
     * @Primary 具有多个缓存管理器，必须得指定一个默认的缓存管理器,springboot1.5.9要制定
     * */
    //@Primary
    @Bean
    public RedisCacheManager myRedisCacheManager(RedisConnectionFactory redisConnectionFactory) {

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                // 设置key的序列化方式
                .entryTtl(Duration.ofSeconds(600000)) // 60s缓存失效
                // 设置key的序列化方式
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                // 设置value的序列化方式
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
                // 不缓存null值
                .disableCachingNullValues();

        RedisCacheManager redisCacheManager= RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config)
                .transactionAware()
                .build();
        System.out.println("自定义RedisCacheManager加载完成");
        return redisCacheManager;

    }
}
