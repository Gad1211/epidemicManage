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
@TableName("temperature")
public class Temperature implements Serializable {
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
     * 用户账户名
     */
    private String userName;

    /**
     * 体温
     */
    private Float temperature;

    /**
     * 日期
     */
    private String date;

    private Date updateTime;
}
