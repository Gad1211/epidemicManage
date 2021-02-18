package com.gad.epidemicmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gad.epidemicmanage.pojo.entity.RealTimeData;

public interface IRealTimeDataService extends IService<RealTimeData> {
    /**
     * 添加
     */
    void addRealTimeData(RealTimeData realTimeData);
}
