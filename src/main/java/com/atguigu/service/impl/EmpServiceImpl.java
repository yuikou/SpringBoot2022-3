package com.atguigu.service.impl;

import com.atguigu.bean.Emp2;
import com.atguigu.mapper.EmpMapper;
import com.atguigu.service.EmpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp2> implements EmpService {
    //SeviceImpl是mybatis plus提供的service實現類, 讓我們不用花時間去實裝method, 泛型要放目標table的mapper class跟返回類型
}
