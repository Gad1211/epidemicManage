package com.gad.epidemicmanage.pojo.dto;

import lombok.Data;

@Data
public class UserListDto {
    /**
     * 用户名称
     */
    private String userName;

    private Integer pageSize;

    private Integer currentPage;
}
