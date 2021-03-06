package com.gad.epidemicmanage.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("states")
public class States implements Serializable {
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
     * 是否异常
     */
    @TableField(value = "is_abnormal")
    private Integer abnormal;

    /**
     * 是否高风险地区
     */
    @TableField(value = "is_high_risk")
    private Integer highRisk;

    /**
     * 隔离天数
     */
    private Integer homeQuarantineDay;

    private Date updateTime;
}
