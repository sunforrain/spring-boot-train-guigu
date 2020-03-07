package com.atguigu.springboot.mapper;

import com.atguigu.springboot.bean.Department;
import org.apache.ibatis.annotations.*;

// 视频64 尚硅谷_SpringBoot_数据访问-整合MyBatis（二）-注解版MyBatis
// 指定这是一个操作数据库的mapper
// mybatis的配置类设置@MapperScan后就不需要@Mapper指定了
//@Mapper
public interface DepartmentMapper {
    // 可以看到以前配置文件里面写的sql语句都可以用注解写了
    @Select("select * from department where id=#{id}")
    public Department getDeptById (Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById (Integer id);

    // 配置应用自增的主键,这样返回给页面的json数据中才能有主键
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department(departmentName)  values (#{departmentName})")
    public int insertDept (Department department);

    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    public int updateDept (Department department);
}
