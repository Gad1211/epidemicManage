package com.gad.epidemicmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gad.epidemicmanage.common.GlobalConstant;
import com.gad.epidemicmanage.common.utils.CommonUtil;
import com.gad.epidemicmanage.pojo.dto.OutRecordDto;
import com.gad.epidemicmanage.pojo.dto.RouteDto;
import com.gad.epidemicmanage.pojo.entity.OutRecord;
import com.gad.epidemicmanage.pojo.entity.Route;
import com.gad.epidemicmanage.pojo.entity.States;
import com.gad.epidemicmanage.pojo.vo.Result;
import com.gad.epidemicmanage.service.IOutRecordService;
import com.gad.epidemicmanage.service.IRouteService;
import com.gad.epidemicmanage.service.IStatesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author  guoandong
 * @date  2021/3/7 14:52
 * @desc  行程管理controller
 **/
@Slf4j
@RestController
public class TripRecordController {

    @Resource
    IRouteService routeService;

    @Resource
    IOutRecordService outRecordService;

    @Resource
    IStatesService statesService;

    /**
     * 新增外出记录申请
     */
    @PostMapping("/insertOutRecord")
    public Result insertOutRecord(@RequestBody OutRecord outRecord) {
        log.info("开始新增外出记录申请");
        Result result = new Result(true, "新增外出记录申请成功");
        try {
            if(outRecord.getUserId() == null || "".equals(outRecord.getOutStartTime())){
                result.setMessage("新增失败，请检查输入");
                return result;
            }
            //flag= 1允许外出，0不允许
            Integer flag = outRecordService.insertOutRecord(outRecord);
            if (flag == GlobalConstant.STATE_TRUE) {
                log.info("新增外出记录申请成功");
            } else {
                log.info("新增外出记录申请失败,用户状态异常");
                result.setCode(GlobalConstant.REQUEST_SUCCESS_STATUS_CODE);
                result.setMessage("新增外出记录申请成功失败,用户状态异常");
            }
        } catch (Exception e) {
            log.error("新增外出记录申请失败：" + e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("新增外出记录申请失败");
        }
        return result;
    }

    /**
     * 查询外出记录
     */
    @PostMapping("/queryOutRecords")
    public Result queryOutRecords(@RequestBody OutRecordDto outRecordDto) {
        log.info("开始查询外出记录申请");
        Result result = new Result(true, "查询外出记录申请成功");
        try {
            IPage<OutRecord> page = outRecordService.queryOutRecords(outRecordDto);
            result.setData(page);
            log.info("查询外出记录申请成功");
        } catch (Exception e) {
            log.error("查询外出记录申请失败：" + e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("查询外出记录申请失败");
        }
        return result;
    }

    /**
     * 删除外出申请记录
     */
    @PostMapping("/deleteOutRecord/{id}")
    public Result deleteOutRecordsById(@PathVariable String id) {
        log.info("开始删除外出记录申请");
        Result result = new Result(true, "删除外出记录申请成功");
        try {
            outRecordService.removeById(id);
            log.info("删除外出记录申请成功");
        } catch (Exception e) {
            log.error("删除外出记录申请失败：" + e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("删除外出记录申请失败");
        }
        return result;
    }

    /**
     * 新增行程记录
     */
    @PostMapping("/insertRoute")
    public Result insertRoute(@RequestBody Route route) {
        log.info("开始新增行程记录");
        Result result = new Result(true, "新增行程记录成功");
        try {
            if("".equals(route.getStartPlace()) || "".equals(route.getEndPlace()) ||
                    "".equals(route.getStartTime()) || "".equals(route.getEndTime()) ||
                    "".equals(route.getVehicle()) || "".equals(route.getVehicleSeatNumber())){
                result.setMessage("新增行程记录失败，请检查输入");
                return result;
            }

            routeService.insertRoute(route);
            log.info("新增行程记录成功");
        } catch (Exception e) {
            log.error("新增行程记录失败：" + e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("新增行程记录失败");
        }
        return result;
    }

    /**
     * 查询行程记录
     */
    @PostMapping("/queryRoutes")
    public Result queryRoutes(@RequestBody RouteDto routeDto) {
        log.info("开始查询行程记录");
        Result result = new Result(true, "查询行程记录成功");
        try {
            IPage<Route> page = routeService.queryRoutes(routeDto);
            result.setData(page);
            log.info("查询行程记录成功");
        } catch (Exception e) {
            log.error("查询行程记录失败：" + e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("查询行程记录失败");
        }
        return result;
    }

    /**
     * 删除行程记录
     */
    @PostMapping("/deleteRoute/{id}")
    public Result deleteRoute(@PathVariable Integer id) {
        log.info("开始删除行程记录");
        Result result = new Result(true, "删除行程记录成功");
        try {
            routeService.removeById(id);
            log.info("删除行程记录成功");
        } catch (Exception e) {
            log.error("删除行程记录失败：" + e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("删除行程记录失败");
        }
        return result;
    }

    /**
     * 导出行程记录
     */
    @GetMapping(value = "/exportRouteExcel")
    public void exportContactsExcel(HttpServletResponse response) {
        log.info("开始导出行程Excel");
        try {
            routeService.exportRouteExcel(response);
            log.info("导出导出行程Excel成功");
        } catch (Exception e) {
            log.error("导出导出行程Excel异常", e);
        }
    }

    /**
     * 查询剩余隔离天数
     */
    @GetMapping(value = "/homeQuarantineDay/{userId}")
    public Result homeQuarantineDay(@PathVariable Integer userId){
        log.info("开始查询隔离天数");
        Result result = new Result(true, "查询隔离天数成功");
        try {
            States states = statesService.getOne(new LambdaQueryWrapper<States>().eq(States::getUserId,userId));
            log.info("查询隔离天数成功");
            result.setData(states.getHomeQuarantineDay());
        } catch (Exception e) {
            log.error("查询隔离天数失败：" + e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("查询隔离天数失败");
        }
        return result;
    }
}