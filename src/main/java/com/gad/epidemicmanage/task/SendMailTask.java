package com.gad.epidemicmanage.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gad.epidemicmanage.common.utils.MailUtil;
import com.gad.epidemicmanage.pojo.entity.UserBaseInfo;
import com.gad.epidemicmanage.service.IUserBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author  guoandong
 * @date  2021/3/13 16:03
 * @desc  邮件发送单次任务
 **/
@Slf4j
@Component
@DisallowConcurrentExecution
public class SendMailTask implements Job {

    @Resource
    IUserBaseInfoService userBaseInfoService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Integer userId = jobExecutionContext.getJobDetail().getJobDataMap().getInt("userId");
        //获取用户基本信息
        UserBaseInfo userBaseInfo = userBaseInfoService.getOne(new LambdaQueryWrapper<UserBaseInfo>().eq(UserBaseInfo::getUserId,userId));
        try{
            MailUtil.sendMail(userBaseInfo);
            log.info("邮件发送成功!");
        }catch (Exception e){
            log.error("邮件发送失败!");
        }
    }
}
