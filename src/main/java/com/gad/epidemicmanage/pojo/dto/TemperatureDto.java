package com.gad.epidemicmanage.pojo.dto;

import lombok.Data;

@Data
public class TemperatureDto {

    /**
     * 用户id 必填
     */
    private Integer userId;

    /**
     * 日期 非必填
     */
    private String date;

    private Integer pageSize;

    private Integer currentPage;
}
