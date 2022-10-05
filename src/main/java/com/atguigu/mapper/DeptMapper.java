package com.atguigu.mapper;

import com.atguigu.bean.Dept;
import org.apache.ibatis.annotations.*;

//@Mapper
public interface DeptMapper {

    @Select("select * from DEPARTMENT where deptno=#{id}")
    public Dept getDeptById(Integer id);

    @Delete("delete from DEPARTMENT where id=#{id}")
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")       //使用自增主鍵
    @Insert("insert into DEPARTMENT (deptno, dname, loc) VALUES (#{deptNo}, #{dName},#{loc})")
    public Dept insertDept(Dept dept);

    @Update("update DEPARTMENT set dname=#{dName} where id=#{id}")
    public int updateDept(Dept dept);
}
