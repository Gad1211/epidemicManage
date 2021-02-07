package com.gad.epidemicmanage.util;

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
     * return Date
     * */
    public static Date dateFormate(String date){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

    /**
     * 获取当前日期并转型为yyyy-MM-dd格式
     * return String
     * */
    public static String todayDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

}
