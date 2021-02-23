package com.gad.epidemicmanage.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author  guoandong
 * @date  2021/2/22 17:48
 * @desc  注入工具
 **/
@Component
public class ApplicationContextUtil extends ApplicationObjectSupport {

    private static ApplicationContext instance;

    public static ApplicationContext getContext() {
        return instance;
    }

    @PostConstruct
    private void init() {
        instance = getApplicationContext();
    }
}
