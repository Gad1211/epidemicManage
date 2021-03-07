package com.gad.epidemicmanage.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("route")
public class Route implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 出发地
     */
    private String startPlace;

    /**
     * 到达地
     */
    private String endPlace;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 到达时间
     */
    private String endTime;

    /**
     * 交通工具
     */
    private String vehicle;

    /**
     * 座次
     */
    private String vehicleSeatNumber;

    private Date updateTime;
}
