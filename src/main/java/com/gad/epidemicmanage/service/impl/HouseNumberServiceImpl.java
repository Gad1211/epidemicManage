package com.gad.epidemicmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gad.epidemicmanage.mapper.HouseNumberMapper;
import com.gad.epidemicmanage.pojo.entity.HouseNumber;
import com.gad.epidemicmanage.service.IHouseNumberService;
import org.springframework.stereotype.Service;

@Service
public class HouseNumberServiceImpl extends ServiceImpl<HouseNumberMapper, HouseNumber> implements IHouseNumberService {
}
