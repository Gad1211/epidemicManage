package com.gad.epidemicmanage.pojo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RouteDto {

    private Integer userId;

    private String userName;

    /**
     * 出发地
     */
    private String startPlace;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 到达时间
     */
    private String endTime;

    private Integer pageSize;

    private Integer currentPage;

}
