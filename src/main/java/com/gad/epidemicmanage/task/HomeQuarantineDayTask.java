package com.gad.epidemicmanage.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gad.epidemicmanage.pojo.entity.States;
import com.gad.epidemicmanage.service.IStatesService;
import com.gad.epidemicmanage.service.IJobAndTriggerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
@DisallowConcurrentExecution
public class HomeQuarantineDayTask implements Job {

    @Resource
    IStatesService statesService;

    @Resource
    IJobAndTriggerService jobAndTriggerService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Integer userId = jobExecutionContext.getJobDetail().getJobDataMap().getInt("userId");
        States states = statesService.getOne(new LambdaQueryWrapper<States>()
                .eq(States::getUserId,userId));

        //获取当前剩余隔离天数
        int curDays = states.getHomeQuarantineDay();

        //0天时删除定时任务
        if (curDays == 0){
            jobAndTriggerService.deleteJob("com.gad.epidemicmanage.task.HomeQuarantineDayTask",
                    "default");
            log.info("userId:" +userId + " 居家隔离已结束");
        }else{
            //减一天后重新存入
            statesService.updateHomeQuarantineDay(userId,curDays-1);
        }

    }
}
