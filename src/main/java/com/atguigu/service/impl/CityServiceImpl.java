package com.atguigu.service.impl;

import com.atguigu.bean.City;
import com.atguigu.mapper.CityMapper;
import com.atguigu.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityMapper cityMapper;

    public City getCityById(Integer id){
        return cityMapper.getCity(id);
    }
}
