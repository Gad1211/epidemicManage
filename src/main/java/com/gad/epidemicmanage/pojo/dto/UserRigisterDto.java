package com.gad.epidemicmanage.pojo.dto;

import lombok.Data;

@Data
public class UserRigisterDto {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 确认密码
     */
    private String userAgainPasaword;

}
