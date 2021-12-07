package com.gad.epidemicmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gad.epidemicmanage.common.GlobalConstant;
import com.gad.epidemicmanage.common.utils.CommonUtil;
import com.gad.epidemicmanage.common.utils.MailUtil;
import com.gad.epidemicmanage.mapper.TemperatureMapper;
import com.gad.epidemicmanage.pojo.dto.TemperatureDto;
import com.gad.epidemicmanage.pojo.entity.Temperature;
import com.gad.epidemicmanage.pojo.entity.User;
import com.gad.epidemicmanage.pojo.entity.UserBaseInfo;
import com.gad.epidemicmanage.service.*;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.security.GeneralSecurityException;

@Slf4j
@Service
public class TemperatureServiceImpl extends ServiceImpl<TemperatureMapper, Temperature> implements ITemperatureService {

    @Resource
    IUserService userService;

    @Resource
    IJobAndTriggerService jobAndTriggerService;

    @Resource
    IStatesService statesService;

    @Override
    public void insertTemperature(Integer userId, Float temperatureNum){
        String date = CommonUtil.todayDate();
        LambdaQueryWrapper<Temperature> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Temperature::getDate,date);
        queryWrapper.eq(Temperature::getUserId,userId);
        //先删除今日原来的
        remove(queryWrapper);
        //保存新的
        Temperature temperature = new Temperature();
        temperature.setUserId(userId);
        temperature.setUserName(userService.getOne(new LambdaQueryWrapper<User>().eq(User::getId,userId)).getUserName());
        temperature.setTemperature(temperatureNum);
        temperature.setDate(date);

        save(temperature);
        //更新states表身体异常 37.5
        if(temperatureNum > GlobalConstant.ABNORMAL_TEMPERATURE){
            statesService.updateTempCondition(userId, GlobalConstant.STATE_TRUE);

            //添加单次任务，异步发送邮件
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("userId",userId);
            jobAndTriggerService.addOnceJob("SendMailTask",
                    "com.gad.epidemicmanage.task.SendMailTask",
                    "default", jobDataMap);

            log.info("体温异常，已更新states");
        }else{
            statesService.updateTempCondition(userId, GlobalConstant.STATE_FALSE);
        }
    }
    @Override
    public IPage<Temperature> queryTemperature(TemperatureDto temperatureDto) {
        LambdaQueryWrapper<Temperature> queryWrapper = new LambdaQueryWrapper<>();
        Page<Temperature> page = new Page<>(temperatureDto.getCurrentPage(), temperatureDto.getPageSize());

        if(temperatureDto.getUserId()!=null){
        //匹配指定用户体温记录
            queryWrapper.eq(Temperature::getUserId,temperatureDto.getUserId());
        }
        if(!temperatureDto.getUserName().isEmpty()){
            //用户名匹配
            queryWrapper.eq(Temperature::getUserName,temperatureDto.getUserName());
        }
        //若日期不为空则查询指定日期
        if(!temperatureDto.getDate().isEmpty()){
            queryWrapper.eq(Temperature::getDate, CommonUtil.dateFormate(temperatureDto.getDate()));
        }

        //日期降序
        queryWrapper.orderByDesc(Temperature::getDate);

        return page(page,queryWrapper);
    }
}
