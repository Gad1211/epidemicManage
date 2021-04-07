package com.gad.epidemicmanage.pojo.dto;

import lombok.Data;

@Data
public class TemperatureDto {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 日期 非必填
     */
    private String date;

    private Integer pageSize;

    private Integer currentPage;
}
