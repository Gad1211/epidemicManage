package com.gad.epidemicmanage.pojo.vo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

@Data
public class UserVo {
    private String userName;

    private Collection<? extends GrantedAuthority> authorities;
}
