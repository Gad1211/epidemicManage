package com.gad.epidemicmanage.service.impl;

import com.gad.epidemicmanage.service.IJobAndTriggerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JobAndTriggerServiceImpl implements IJobAndTriggerService {


    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;


    @Override
    public boolean addJob(String jobName, String jobClassName, String jobGroupName, String cronExpression,JobDataMap map) {
        boolean result = true;
        try {
            // 启动调度器 构建一个新的trigger
            scheduler.start();
            Job baseJob = (Job) Class.forName(jobClassName).newInstance();
            JobDetail jobDetail = JobBuilder.newJob(baseJob.getClass()).withIdentity(jobName, jobGroupName).usingJobData(map).build();
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            result = false;
            log.error("添加quartz定时信息", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean addOnceJob(String jobName, String jobClassName, String jobGroupName, JobDataMap map) {
        boolean result = true;
        try {
            scheduler.start();
            Job baseJob = (Job) Class.forName(jobClassName).newInstance();
            JobDetail jobDetail = JobBuilder.newJob(baseJob.getClass()).withIdentity(jobName, jobGroupName)
                    .usingJobData(map)
                    .build();
            //马上执行 只执行一次
            SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInSeconds(0)
                                    .withRepeatCount(0))
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            result = false;
            log.error("单次任务执行异常", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean pauseJob(String jobName, String jobGroupName) {
        boolean result = true;
        try {
            scheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (SchedulerException e) {
            result = false;
            log.error("暂停定时任务异常", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean resumeJob(String jobName, String jobGroupName) {
        boolean result = true;
        try {
            scheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (SchedulerException e) {
            result = false;
            log.error("恢复定时任务异常", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean rescheduleJob(String jobName, String jobGroupName, String cronExpression) {
        boolean result = true;
        try {
            // 按新的trigger重新设置job执行
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            result = false;
            log.error("更新定时任务异常", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteJob(String jobName, String jobGroupName) {
        boolean result = true;
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, jobGroupName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, jobGroupName));
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (SchedulerException e) {
            result = false;
            log.error("删除定时任务异常", e.getMessage(), e);
        }
        return result;
    }

}
