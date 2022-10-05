package com.atguigu.mapper;

import com.atguigu.bean.Emp2;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 繼承 mybatis plus提供的BaseMapper介面後, 無須編寫mapper.xml文件, 即可獲得CRUD功能
 */
@Mapper
public interface EmpMapper extends BaseMapper<Emp2> {


}
