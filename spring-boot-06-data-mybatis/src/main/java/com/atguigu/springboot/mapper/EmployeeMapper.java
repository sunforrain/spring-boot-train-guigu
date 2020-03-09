package com.atguigu.springboot.mapper;

import com.atguigu.springboot.bean.Employee;

// 视频65 尚硅谷_SpringBoot_数据访问-整合MyBatis（二）-配置版MyBatis
// @Mapper或者@MapperScan将接口扫描装配到容器中
public interface EmployeeMapper {

    public Employee getEmpById (Integer id);

    public void insertEmp(Employee employee);


}
