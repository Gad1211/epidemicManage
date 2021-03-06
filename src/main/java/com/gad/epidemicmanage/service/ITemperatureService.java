package com.gad.epidemicmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gad.epidemicmanage.pojo.dto.TemperatureDto;
import com.gad.epidemicmanage.pojo.entity.Temperature;

public interface ITemperatureService extends IService<Temperature> {

    /**
     * 新增温度数据
     */
    void insertTemperature(Integer userId,Float temperatureNum);

    /**
     * 查询体温数据
     */
    IPage<Temperature> queryTemperature(TemperatureDto temperatureDto);
}
