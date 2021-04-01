package com.gad.epidemicmanage.pojo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;


/**
 * 重写构造
 * Security框架 Detail类 必须实现GrantedAuthority 并重写构造方法
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
