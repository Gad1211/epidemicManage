package com.gad.epidemicmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gad.epidemicmanage.common.GlobalConstant;
import com.gad.epidemicmanage.common.utils.CommonUtil;
import com.gad.epidemicmanage.pojo.dto.TemperatureDto;
import com.gad.epidemicmanage.pojo.entity.Temperature;
import com.gad.epidemicmanage.pojo.vo.Result;
import com.gad.epidemicmanage.service.IStatesService;
import com.gad.epidemicmanage.service.ITemperatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author  guoandong
 * @date  2021/3/6 13:40
 * @desc  健康管理controller
 **/
@Slf4j
@RestController
public class HealthController {

    @Resource
    ITemperatureService temperatureService;

    @Resource
    IStatesService conditionService;

    /**
     * 体温填报
     */
    @PostMapping("/insertTemperature")
    public Result insertTemperature(Integer userId,Float temperatureNum){
        log.info("开始填报体温数据");
        Result result = new Result(true, "体温数据填报成功");
        try{
            //TODO 体温填报异常时的处理
            temperatureService.insertTemperature(userId,temperatureNum);
            log.info("体温数据填报成功");
        }catch (Exception e){
            log.error("体温数据填报失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("体温数据填报失败");
        }
        return result;
    }

    /**
     * 分页查询体温数据
     */
    @PostMapping("/queryTemperatures")
    public Result queryTemperatures(@RequestBody TemperatureDto temperatureDto){
        log.info("开始查询体温数据");
        Result result = new Result(true, "体温数据查询成功");
        try{
            IPage<Temperature> temperaturePage = temperatureService.queryTemperature(temperatureDto);
            result.setData(temperaturePage);
            log.info("体温数据查询成功");
        }catch (Exception e){
            log.error("体温数据查询失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("体温数据查询失败");
        }
        return result;
    }

    /**
     * 体温删除
     */
    @PostMapping("/deleteTemperature")
    public Result deleteTemperature(Integer userId,String date){
        log.info("删除体温数据开始");
        Result result = new Result(true, "删除体温数据成功");
        try{
            temperatureService.remove(new LambdaQueryWrapper<Temperature>()
                .eq(Temperature::getUserId,userId)
                .eq(Temperature::getDate, CommonUtil.dateFormate(date))
            );
            log.info("删除体温数据成功");
        }catch (Exception e){
            log.error("删除体温数据失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("删除体温数据失败");
        }
        return result;
    }
}
