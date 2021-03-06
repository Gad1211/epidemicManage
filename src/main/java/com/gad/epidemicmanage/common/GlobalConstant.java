package com.gad.epidemicmanage.common;

/**
 * @Description 全局常量
 */
public class GlobalConstant {

    /**
     * 实时数据网址
     */
    public static final String REAL_TIME_URL = "https://voice.baidu.com/act/newpneumonia/newpneumonia/?from=osari_aladin_banner#tab0";

    /**
     * 请求成功状态码
     */
    public static final Integer REQUEST_SUCCESS_STATUS_CODE = 200;

    /**
     * 请求成功消息
     */
    public static final String REQUEST_SUCCESS_STATUS_MESSAGE = "请求成功";

    /**
     * 请求错误状态码
     */
    public static final Integer REQUEST_ERROR_STATUS_CODE = 500;

    /**
     * 请求错误消息
     */
    public static final String REQUEST_ERROR_STATUS_MESSAGE = "请求失败";

    /**
     * 管理员角色
     */
    public static final String ROLE_ADMIN = "role_admin";

    /**
     * 普通角色
     */
    public static final String ROLE_USER = "role_user";

    /**
     * 通用状态 0为否
     */
    public static final int STATE_FALSE = 0;

    /**
     * 通用状态 1为是
     */
    public static final int STATE_TRUE = 1;

}
