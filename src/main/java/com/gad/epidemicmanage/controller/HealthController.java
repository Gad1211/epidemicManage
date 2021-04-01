package com.gad.epidemicmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gad.epidemicmanage.common.GlobalConstant;
import com.gad.epidemicmanage.common.utils.CommonUtil;
import com.gad.epidemicmanage.pojo.dto.TemperatureDto;
import com.gad.epidemicmanage.pojo.entity.States;
import com.gad.epidemicmanage.pojo.entity.Temperature;
import com.gad.epidemicmanage.pojo.vo.Result;
import com.gad.epidemicmanage.service.IStatesService;
import com.gad.epidemicmanage.service.ITemperatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    IStatesService statesService;

    /**
     * 体温填报
     */
    @PostMapping("/insertTemperature")
    public Result insertTemperature(Integer userId,Float temperature){
        log.info("开始填报体温数据");
        Result result = new Result(true, "体温数据填报成功");
        try{
            if(temperature <= 0 || temperature >= 45){
                log.info("体温数据非法，请检查填入的体温");
                result.setMessage("体温数据非法，请检查填入的体温");
                return result;
            }
            temperatureService.insertTemperature(userId,temperature);
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
    @PostMapping("/deleteTemperature/{id}")
    public Result deleteTemperature(@PathVariable Integer id){
        log.info("删除体温数据开始");
        Result result = new Result(true, "删除体温数据成功");
        try{
            temperatureService.removeById(id);
            log.info("删除体温数据成功");
        }catch (Exception e){
            log.error("删除体温数据失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("删除体温数据失败");
        }
        return result;
    }

    /**
     * 查询异常体温
     */
    @GetMapping("/queryAbnormalTemperature")
    public Result queryAbnormalInfo(){
        log.info("查询异常体温开始");
        Result result = new Result(true, "查询异常体温成功");
        try{
            List<States> res = statesService.list(new LambdaQueryWrapper<States>()
                    .eq(States::getAbnormal,GlobalConstant.STATE_TRUE));
            result.setData(res);
            log.info("查询异常体温成功");
        }catch (Exception e){
            log.error("查询异常体温失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("查询异常体温失败");
        }
        return result;
    }

    /**
     * 查询高风险地区
     */
    @GetMapping("/queryHighRiskArea")
    public Result queryHighRiskArea(){
        log.info("查询高风险地区开始");
        Result result = new Result(true, "查询高风险地区成功");
        try{
            List<States> res = statesService.list(new LambdaQueryWrapper<States>()
                    .eq(States::getHighRisk,GlobalConstant.STATE_TRUE));

            result.setData(res);
            log.info("查询高风险地区成功");
        }catch (Exception e){
            log.error("查询高风险地区失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("查询高风险地区失败");
        }
        return result;
    }

}
