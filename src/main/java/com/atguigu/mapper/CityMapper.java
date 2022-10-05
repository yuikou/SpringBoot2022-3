package com.atguigu.mapper;

import com.atguigu.bean.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

//@Mapper或MapScanner裝配到容器
public interface CityMapper {

    public City getCity(Integer id);

    public void insertCity(City city);
}
