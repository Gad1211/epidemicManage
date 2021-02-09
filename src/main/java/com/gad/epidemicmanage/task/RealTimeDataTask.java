package com.gad.epidemicmanage.task;

import com.gad.epidemicmanage.common.GlobalConstant;
import com.gad.epidemicmanage.pojo.entity.RealTimeData;
import com.gad.epidemicmanage.service.IRealTimeDataService;
import com.gad.epidemicmanage.util.CommonUtil;
import com.gad.epidemicmanage.util.HttpUnitUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 获取国内疫情实时数据
 */
@Slf4j
@Component
@DisallowConcurrentExecution
public class RealTimeDataTask implements Job {
    @Resource
    private IRealTimeDataService realTimeDataService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        int i = 0;
        int maxTimes = 5;

        while(i < maxTimes){
            String html = HttpUnitUtil.getHtmlPage(GlobalConstant.REAL_TIME_URL);
            if ("".equals(html)) {
                log.error("国内实时数据获取页面失败");
                return;
            }else {
                System.out.println(html);
                log.info("国内实时数据获取页面成功，开始第 "+ (i+1) +" 次分析");
            }

            try{
                Document document = Jsoup.parse(html);

                RealTimeData realTimeData = new RealTimeData();
                //地区
                Element e1 = document.getElementsByClass("Virus_1-1-296_3U87rg").get(0);
                realTimeData.setPlace(e1.text());
                //现存确诊
                Element e2 = document.getElementsByClass("VirusSummarySix_1-1-296_2ZJJBJ").get(0);
                realTimeData.setExitDiagnosis(CommonUtil.strToNum(e2.text()));
                //累计确诊
                Element e3 = document.getElementsByClass("VirusSummarySix_1-1-296_2ZJJBJ").get(4);
                realTimeData.setCountDiagnosis(CommonUtil.strToNum(e3.text()));
                //境外输入
                Element e4 = document.getElementsByClass("VirusSummarySix_1-1-296_2ZJJBJ").get(5);
                realTimeData.setAbroad(CommonUtil.strToNum(e4.text()));
                //无症状
                Element e5 = document.getElementsByClass("VirusSummarySix_1-1-296_2ZJJBJ").get(1);
                realTimeData.setAsymptomatic(CommonUtil.strToNum(e5.text()));
                //现存疑似
                Element e6 = document.getElementsByClass("VirusSummarySix_1-1-296_2ZJJBJ").get(2);
                realTimeData.setExitSuspected(CommonUtil.strToNum(e6.text()));
                //现存重症
                Element e7 = document.getElementsByClass("VirusSummarySix_1-1-296_2ZJJBJ").get(3);
                realTimeData.setExitSevere(CommonUtil.strToNum(e7.text()));
                //累计治愈
                Element e8 = document.getElementsByClass("VirusSummarySix_1-1-296_2ZJJBJ").get(6);
                realTimeData.setCountCure(CommonUtil.strToNum(e8.text()));
                //累计死亡
                Element e9 = document.getElementsByClass("VirusSummarySix_1-1-296_2ZJJBJ").get(7);
                realTimeData.setCountDeath(CommonUtil.strToNum(e9.text()));
                //当天日期
                realTimeData.setDate(CommonUtil.todayDate());
                log.info("页面数据分析完毕，存入数据库");
                realTimeDataService.addRealTimeData(realTimeData);
                log.info("国内实时数据已保存");
                break;
            }catch (Exception e){
                log.error("第 "+ (i+1) +" 次国内分析实时疫情数据异常:"+e);
                i++;
            }

        }
    }
}
