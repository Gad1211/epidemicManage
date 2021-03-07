package com.gad.epidemicmanage.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class OutRecordDto {

    /**
     *主键,时间加用户id生成
     */
    private String id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 外出时间
     */
    private String outStartTime;

    /**
     * 返回时间
     */
    private String outBackTime;

    private Integer pageSize;

    private Integer currentPage;
}
