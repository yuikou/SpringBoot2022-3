package com.atguigu.controller;

import com.atguigu.bean.Dept;
import com.atguigu.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {

    @Autowired
    DeptMapper deptMapper;


    @GetMapping("/dept/{id}")
    public Dept getDept(@PathVariable("id") Integer id){
        return deptMapper.getDeptById(id);
    }

    @GetMapping("/dept")
    public Dept insertDept(Dept dept){
        dept.setDeptNo(15);
        dept.setDName("MIS");
        dept.setLoc("台北淡水");

        deptMapper.insertDept(dept);
        return dept;
    }

}
