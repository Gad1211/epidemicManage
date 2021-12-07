package com.gad.epidemicmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gad.epidemicmanage.common.utils.CommonUtil;
import com.gad.epidemicmanage.pojo.entity.RealTimeData;
import com.gad.epidemicmanage.pojo.vo.Result;
import com.gad.epidemicmanage.service.IRealTimeDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author  guoandong
 * @date  2021/3/12 16:24
 * @desc  展示事实疫情数据
 **/
@Slf4j
@RestController
public class RealTimeDataController {

    @Resource
    IRealTimeDataService realTimeDataService;

    @GetMapping("/getRealTimeData/{date}")
    public Result getRealTimeData(@PathVariable String date){
        Result result = new Result(true,"返回当天疫情数据成功");

        try{
            RealTimeData realTimeData = realTimeDataService.getOne(new LambdaQueryWrapper<RealTimeData>()
                    .eq(RealTimeData::getDate,date));
            //没获取到则返回前一天的数据
            if(realTimeData == null){
                realTimeData = realTimeDataService.getOne(new LambdaQueryWrapper<RealTimeData>()
                        .eq(RealTimeData::getDate, CommonUtil.getOneDayBefore(date)));
            }
            result.setData(realTimeData);
            log.info("返回当天疫情数据成功");
        }catch (Exception e){
            result.setMessage("返回当前疫情数据失败");
            log.error("返回当前疫情数据失败");
        }
        return result;
    }
}
