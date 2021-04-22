package com.gad.epidemicmanage.pojo.dto;

import lombok.Data;

@Data
public class UpdatePasswdDto {
    private Integer userId;

    private String oldPassword;

    private String newPassword;

    private String newRePssword;
}
