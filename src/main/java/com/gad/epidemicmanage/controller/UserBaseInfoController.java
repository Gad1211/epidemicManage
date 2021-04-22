package com.gad.epidemicmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gad.epidemicmanage.common.GlobalConstant;
import com.gad.epidemicmanage.pojo.dto.UserBaseInfoDto;
import com.gad.epidemicmanage.pojo.entity.UserBaseInfo;
import com.gad.epidemicmanage.pojo.vo.Result;
import com.gad.epidemicmanage.service.IStatesService;
import com.gad.epidemicmanage.service.IJobAndTriggerService;
import com.gad.epidemicmanage.service.IUserBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.gad.epidemicmanage.common.GlobalConstant.STATE_FALSE;
import static com.gad.epidemicmanage.common.GlobalConstant.STATE_TRUE;

/**
 * @author  guoandong
 * @date  2021/3/6 15:34
 * @desc  用户基础信息controller
 **/
@Slf4j
@RestController
public class UserBaseInfoController {

    @Resource
    IUserBaseInfoService userBaseInfoService;

    @Resource
    IStatesService statesService;

    @Resource
    IJobAndTriggerService jobAndTriggerService;

    /**
     * 新增用户基本信息
     */
    @Transactional
    @PostMapping("/insertBaseInfo")
    public Result insertBaseInfo(@RequestBody UserBaseInfoDto userBaseInfoDto){
        log.info("新增用户基本信息开始");
        Result result = new Result(true, "新增用户基本信息成功");
        //设置回滚点
        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        try{
            //校验
            if(userBaseInfoDto.getUserId() == null || "".equals(userBaseInfoDto.getName()) || "".equals(userBaseInfoDto.getPhone())){
                result.setMessage("注册失败，请检查输入");
                return result;
            }

            //保存基本信息
            userBaseInfoService.insertUserBaseInfo(userBaseInfoDto);

            //体温异常
            if(userBaseInfoDto.getAbnormal() == STATE_TRUE && userBaseInfoDto.getHighRisk() == STATE_FALSE){
                statesService.updateCondition(userBaseInfoDto.getUserId(),STATE_TRUE,STATE_FALSE);
                log.info("该用户身体异常");
             //风险回归
            }else if (userBaseInfoDto.getHighRisk() == STATE_TRUE && userBaseInfoDto.getAbnormal() == STATE_FALSE){
                statesService.updateCondition(userBaseInfoDto.getUserId(),STATE_FALSE,STATE_TRUE);
                statesService.updateHomeQuarantineDay(userBaseInfoDto.getUserId(),14);
                JobDataMap map = new JobDataMap();
                map.put("userId", userBaseInfoDto.getUserId());
                //添加计数隔离天数定时任务
                jobAndTriggerService.addJob("HomeQuarantineDayTask",
                        "com.gad.epidemicmanage.task.HomeQuarantineDayTask",
                        "default", "01 00 00 * * ? ",map);
                log.info("该用户从高风险地区返乡,进行14天居家隔离");
            }else if (userBaseInfoDto.getHighRisk() == STATE_TRUE && userBaseInfoDto.getAbnormal() == STATE_TRUE){
                statesService.updateCondition(userBaseInfoDto.getUserId(),STATE_TRUE,STATE_TRUE);
                statesService.updateHomeQuarantineDay(userBaseInfoDto.getUserId(),14);
                JobDataMap map = new JobDataMap();
                map.put("userId", userBaseInfoDto.getUserId());
                //添加计数隔离天数定时任务
                jobAndTriggerService.addJob("HomeQuarantineDayTask",
                        "com.gad.epidemicmanage.task.HomeQuarantineDayTask",
                        "default", "01 00 00 * * ? ",map);
                log.info("该用户从高风险地区返乡,且体温异常");
            }

            log.info("新增用户基本信息成功");
        }catch (Exception e){
            //回滚事务
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
            log.error("新增用户基本信息失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("新增用户基本信息失败");
        }
        return result;
    }


    /**
     * 查询指定用户基础信息
     */
    @GetMapping("/queryBaseInfo/{userId}")
    public Result queryBaseInfo(@PathVariable Integer userId){
        log.info("开始查询用户基础信息");
        Result result = new Result(true, "查询用户基础信息成功");
        try{
            UserBaseInfo userBaseInfo = userBaseInfoService.getOne(new LambdaQueryWrapper<UserBaseInfo>()
                    .eq(UserBaseInfo::getUserId,userId));
            result.setData(userBaseInfo);
            log.info("查询用户基础信息成功");
        }catch (Exception e){
            log.error("查询用户基础信息失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("查询用户基础信息失败");
        }
        return result;
    }

    /**
     * 修改用户详细信息
     * @return
     */
    @PostMapping("/updateBaseInfo")
    public Result updateBaseInfo(@RequestBody UserBaseInfo userBaseInfo){
        log.info("修改用户基础信息开始");
        Result result = new Result(true, "修改用户基础信息成功");
        try{
            //校验
            if(userBaseInfo.getUserId() == null || "".equals(userBaseInfo.getName()) || "".equals(userBaseInfo.getPhone())){
                result.setMessage("修改失败，请检查输入");
                return result;
            }

            userBaseInfoService.updateUserBaseInfo(userBaseInfo);
            log.info("修改用户基础信息成功");
        }catch (Exception e){
            log.error("修改用户基础信息失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("修改用户基础信息失败");
        }
        return result;
    }

}
