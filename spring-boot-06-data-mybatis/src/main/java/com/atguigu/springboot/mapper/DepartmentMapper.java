package com.atguigu.springboot.mapper;

import com.atguigu.springboot.bean.Department;
import org.apache.ibatis.annotations.*;

// 指定这是一个操作数据库的mapper
// 这里演示的是mybatis的注解版使用方法
//@Mapper
public interface DepartmentMapper {
    // 可以看到以前配置文件里面写的sql语句都可以用注解写了
    @Select("select * from department where id=#{id}")
    public Department getDeptById (Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById (Integer id);

    // 配置应用自增的主键,这样返回给页面的json数据中才能有主键
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department(department_name)  values (#{departmentName})")
    public int insertDept (Department department);

    @Update("update department set department_name=#{departmentName} where id=#{id}")
    public int updateDept (Department department);
}
