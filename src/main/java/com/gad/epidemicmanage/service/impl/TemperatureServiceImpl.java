package com.gad.epidemicmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gad.epidemicmanage.mapper.TemperatureMapper;
import com.gad.epidemicmanage.pojo.entity.Temperature;
import com.gad.epidemicmanage.service.ITemperatureService;
import org.springframework.stereotype.Service;

@Service
public class TemperatureServiceImpl extends ServiceImpl<TemperatureMapper, Temperature> implements ITemperatureService {
}
