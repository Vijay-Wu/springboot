package pers.vergil.restfulcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pers.vergil.restfulcrud.dao.DepartmentDao;
import pers.vergil.restfulcrud.dao.EmployeeDao;
import pers.vergil.restfulcrud.entities.Department;
import pers.vergil.restfulcrud.entities.Employee;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    EmployeeDao employeeDao;

    @GetMapping("/emps")
//    查询所有员工返回页面
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();

//        放在请求域中共享
        model.addAttribute("emps", employees);
//        thymeleaf默认拼串
//        classpath:/Templates/xxx.html
        return "emp/list";
    }

    //    来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model) {
//        查出所有部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    //    员工添加
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        employeeDao.save(employee);
        //redirect:重定向,  /代表当前项目路径
        //forward:转发
        return "redirect:/emps";
    }

    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        //页面要显示所有的部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //回到修改页面(add是一个修改添加二合一的页面);
        return "emp/add";
    }
    //员工修改
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
