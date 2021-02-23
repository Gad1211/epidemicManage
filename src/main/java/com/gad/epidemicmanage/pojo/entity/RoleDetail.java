package com.gad.epidemicmanage.pojo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;


/**
 * 重写构造
 *
 */
@Data
public class RoleDetail implements GrantedAuthority {

    private Role role;

    public RoleDetail(Role role){
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.getRole();
    }
}
