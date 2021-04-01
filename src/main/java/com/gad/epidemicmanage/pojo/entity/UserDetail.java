package com.gad.epidemicmanage.pojo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * Security框架 Detail类 必须实现UserDetails 并重写构造方法
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
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }


    /**
     *   账号是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     *   是否锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     *   账号密码是否锁定
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
