package com.gad.epidemicmanage.task;

import com.gad.epidemicmanage.service.IJobAndTriggerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Description: 定义启动时配置定时任务
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TaskStart implements ApplicationRunner {

    private final ConfigurableApplicationContext context;

    private final IJobAndTriggerService jobAndTriggerService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (context.isActive()) {
            /**
             * 获取实时疫情数据,每天更新两次
             */
            JobDataMap map = new JobDataMap();
            jobAndTriggerService.addJob("RealTimeDataTask",
                    "com.gad.epidemicmanage.task.RealTimeDataTask",
                    "default", "10 00 09,18 * * ? ",map);
        }
    }
}
