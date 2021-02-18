package com.gad.epidemicmanage.pojo.vo;

import lombok.Data;

/**
 * @author  guoandong
 * @date  2021/2/18 10:50
 * @desc  登录数据展示
 **/
@Data
public class LoginVo {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名称
     */
    private String userName;
}
