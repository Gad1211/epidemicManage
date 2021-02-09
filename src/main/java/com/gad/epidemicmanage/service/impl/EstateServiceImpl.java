package com.gad.epidemicmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gad.epidemicmanage.mapper.EstateMapper;
import com.gad.epidemicmanage.pojo.entity.Estate;
import com.gad.epidemicmanage.service.IEstateService;
import org.springframework.stereotype.Service;

@Service
public class EstateServiceImpl  extends ServiceImpl<EstateMapper, Estate> implements IEstateService {
}
