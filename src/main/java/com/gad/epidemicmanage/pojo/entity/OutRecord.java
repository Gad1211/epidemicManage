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
@TableName("out_record")
public class OutRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *主键,时间加用户id生成
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 事情
     */
    private String thing;

    /**
     * 外出时间
     */
    private Date outStartTime;

    /**
     * 返回时间
     */
    private Date outBackTime;

    private Date updateTime;
}
