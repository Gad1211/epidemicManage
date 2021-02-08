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
@TableName("real_time_data")
public class RealTimeData implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 地区
     */
    private String place;
    /**
     * 日期
     */
    private String date;
    /**
     * 现存确诊
     */
    @TableField(value = "e_diagnosis")
    private Integer exitDiagnosis;

    /**
     * 累计确诊
     */
    @TableField(value = "c_diagnosis")
    private Integer countDiagnosis;

    /**
     * 境外输入
     */
    private Integer abroad;

    /**
     * 无症状
     */
    private Integer asymptomatic;

    /**
     * 现存疑似
     */
    @TableField(value = "e_suspected")
    private Integer exitSuspected;

    /**
     * 现存重症
     */
    @TableField(value = "e_severe")
    private Integer exitSevere;

    /**
     * 累计治愈
     */
    @TableField(value = "c_cure")
    private Integer countCure;

    /**
     * 累计死亡
     */
    @TableField(value = "c_death")
    private Integer countDeath;

    private Date updateTime;
}
