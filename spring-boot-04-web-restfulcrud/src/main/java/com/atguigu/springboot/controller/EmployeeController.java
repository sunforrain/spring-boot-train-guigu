package com.atguigu.springboot.controller;

import com.atguigu.springboot.dao.DepartmentDao;
import com.atguigu.springboot.dao.EmployeeDao;
import com.atguigu.springboot.entities.Department;
import com.atguigu.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    // 查询所有员工返回列表页面
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        // 放在请求域中
        model.addAttribute("emps", employees);
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "emps/list";
    }
    // 来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        // 来到添加页面,查出所有的部门,在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emps/add";
    }

    // 员工添加
    // springMVC自动将请求参数和入参对象的属性进行一一绑定:要求请求参数的名字和javaBean入参对象里面的属性名是一样的
    // 因此需要修改前台页面中input的name属性和javaBean里面属性名一致
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        // 添加完毕来到员工列表页面
        System.out.println("保存的员工信息:"+ employee);
        // 保存员工
        employeeDao.save(employee);
        // 不能像下面这样去写模板引擎的页面,这样只是打开了页面,但是表单提交的信息还在
//        return "emps/list";
        // 正确的做法是用redirect(重定向)或forward(转发)进行一次真正的请求,而不是打开一个模板引擎的页面
        // redirect和forward的原理见ThymeleafViewResolver.java
        return "redirect:/emps";
    }

    // 来到修改页面查出当前员工,在页面回显
    // 获取到传入的参数需要用到{}的写法以及@PathVariable注解
    @GetMapping("/emp/{id}")
    public String toEditPage (@PathVariable("id") Integer id, Model model) {
        // 查出员工并放在model中
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        // 查出所有的部门,在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        // 回到修改页面(add是一个修改添加二合一的页面)
        return "emps/add";
    }

    // 员工修改:需要提交员工id
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("修改的员工数据:" +employee);
        employeeDao.save(employee);
        // 修改完还是要重定向的
        return "redirect:/emps";
    }

    // 员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
