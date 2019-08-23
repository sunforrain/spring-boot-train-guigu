package com.atguigu.springboot.entity;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import javax.persistence.*;

// 配置映射关系,使用jpa注解
@Entity // 告诉JPA这是一个实体类(和数据表映射的类)
@Table(name = "tbl_user") // 指定和哪个数据表对应;如果省略,那默认表名是类名小写
public class User {
    @Id // 这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 自增主键
    private Integer id;

    @Column(name = "last_name")// 这是和数据表对应的一个列
    private String lastName;

    @Column // 省略默认列名就是属性名
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
