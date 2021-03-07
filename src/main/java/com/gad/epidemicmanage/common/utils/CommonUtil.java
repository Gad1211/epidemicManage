package com.gad.epidemicmanage.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通用工具集
 */
@Slf4j
public class CommonUtil {
    /**
     * 将字符串转型为yyyy-MM-dd格式
     * return java.sql.Date
     * */
    public static java.sql.Date dateFormate(String date){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        java.sql.Date date2 = null;
        try {
            date1 = format.parse(date);
            date2 = new java.sql.Date(date1.getTime());
        } catch (ParseException e) {
            log.error("字符串转型为yyyy-MM-dd格式异常，请检查入参");
        } catch (NullPointerException e){
            log.error("空指针异常，请检查入参");
        }

        return date2;
    }

    /**
     * 获取当前日期并转型为yyyy-MM-dd格式
     * return String
     * */
    public static String todayDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 外出记录时间戳 年月日时分
     */
    public static String getRecordTimeStamp(){ return new SimpleDateFormat("yyyyMMddHHmm").format(new Date()); }

    /**
     * 含有逗号的字符串转数字
     * 因场景原因，不处理负数
     * 如果不含有逗号的，直接转换为int传回
     */
    public static int strToNum(String str){
        int res = 0;
        try{
            String[] strList = str.split(",");
            double j = 0;
            for(int i=strList.length-1; i>=0; i--){
                res += Double.parseDouble(strList[i]) * Math.pow(1000.0,j);
                j++;
            }
        }catch (Exception e){
            log.error("含有逗号的字符串转数字异常，请检查入参");
        }
        return res;
    }
}
