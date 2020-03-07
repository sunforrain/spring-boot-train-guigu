package com.atguigu.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class HelloController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 视频61 尚硅谷_SpringBoot_数据访问-JDBC&自动配置原理,测试连接下数据源,指定自动运行语句的路径
     * 测试springboot整合JdbcTemplate的方法
     * @return
     */
    @ResponseBody
    @GetMapping("/query")
    public Map<String, Object> map(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from departments");
        return list.get(0);
    }
}
