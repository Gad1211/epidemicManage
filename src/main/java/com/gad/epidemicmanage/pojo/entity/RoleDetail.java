package com.gad.epidemicmanage.pojo.entity;

import lombok.Data;


/**
 * 重写构造
 *  implements GrantedAuthority
 */
@Data
public class RoleDetail{

    private Role role;

    public RoleDetail(Role role){
        this.role = role;
    }
//
//    @Override
//    public String getAuthority() {
//        return role.getRole().toString();
//    }
}
