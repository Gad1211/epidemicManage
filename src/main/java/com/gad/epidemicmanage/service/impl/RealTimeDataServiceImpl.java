package com.gad.epidemicmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gad.epidemicmanage.entity.RealTimeData;
import com.gad.epidemicmanage.mapper.RealTimeDataMapper;
import com.gad.epidemicmanage.service.IRealTimeDataService;
import org.springframework.stereotype.Service;

@Service
public class RealTimeDataServiceImpl extends ServiceImpl<RealTimeDataMapper, RealTimeData> implements IRealTimeDataService {
    @Override
    public void addRealTimeData(RealTimeData realTimeData) {
        LambdaQueryWrapper<RealTimeData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RealTimeData::getDate,realTimeData.getDate());
        //若已存在则删除原来的
        if(getOne(queryWrapper) != null){
            remove(queryWrapper);
        }
        save(realTimeData);
    }
}
