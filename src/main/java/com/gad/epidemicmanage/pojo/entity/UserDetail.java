package com.gad.epidemicmanage.pojo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * 重写构造
 */
@Data
public class UserDetail implements UserDetails {

    private User user;

    private Set<GrantedAuthority> authorities;

    public UserDetail(User user,Set<GrantedAuthority> authorities){
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     *   账号是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     *   是否锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     *   账号密码是否锁定
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
