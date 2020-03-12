package com.atguigu.cache.service;

import com.atguigu.cache.bean.Department;
import com.atguigu.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;


@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Qualifier("myRedisCacheManager")
    @Autowired
    RedisCacheManager myRedisCacheManager;


    /**
     *  视频12 缓存-自定义CacheManager  http://localhost:8080/dept/1
     *  缓存的数据能存入redis；
     *  第二次从缓存中查询就不能反序列化回来；
     *  存的是dept的json数据;CacheManager默认使用RedisTemplate<Object, Employee>操作Redis
     *  这里演示自定义多个CacheManager,即一个Bean对应一个CacheManager的情况
     *  注意springBoot2版本设置CacheManager与一不同了,见MyRedisConfig
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "dept",cacheManager = "myRedisCacheManager")
    public Department getDeptById(Integer id){
        System.out.println("查询部门"+id);
        Department department = departmentMapper.getDeptById(id);
        return department;
    }

    /**
     * 视频12 缓存-自定义CacheManager
     * 使用缓存管理器得到缓存，进行api调用
     * @param id
     * @return
     */
    public Department getDeptByIdOfApi(Integer id){
        System.out.println("查询部门"+id);
        Department department = departmentMapper.getDeptById(id);

        //直接通过Api获取某个缓存
        Cache dept = myRedisCacheManager.getCache("dept");
        dept.put("dept:1",department);

        return department;
    }


}
