package com.atguigu.cache.bean;

import java.io.Serializable;

// 视频3 缓存-基本环境搭建
public class Department implements Serializable {
	
	private Integer id;
	private String departmentName;
	
	
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(Integer id, String departmentName) {
		super();
		this.id = id;
		this.departmentName = departmentName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName + "]";
	}
	
	
	
	

}
