package com.atguigu.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("EMPLOYEE2")     //指定table名稱
public class Emp2 {

    @TableId    //指定PK
    private Integer empno;
    private String ename;
    private String job;
    private Date hiredate;
    private Integer sal;
    private Integer comm;

    @TableField(value = "DEPTNO")   //資料庫欄位名
    private Integer deptNo;

    @TableField(exist = false)      //這個屬性不存在資料庫裏面(不是資料庫欄位)
    private  String gender;

}

