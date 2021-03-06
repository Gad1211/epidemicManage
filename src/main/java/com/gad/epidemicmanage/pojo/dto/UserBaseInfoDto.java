package com.gad.epidemicmanage.pojo.dto;

import lombok.Data;

@Data
public class UserBaseInfoDto {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 社区
     */
    private String community;

    /**
     * 小区
     */
    private String estate;

    /**
     * 门牌号
     */
    private String houseNumber;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 电话
     */
    private String phone;

    /**
     * 是否高风险地区 0为否
     */
    private Integer highRisk;

    /**
     * 是否身体异常 0为否
     */
    private Integer abnormal;

}
