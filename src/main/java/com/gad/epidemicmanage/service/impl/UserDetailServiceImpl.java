package com.gad.epidemicmanage.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gad.epidemicmanage.pojo.entity.Role;
import com.gad.epidemicmanage.pojo.entity.RoleDetail;
import com.gad.epidemicmanage.pojo.entity.User;
import com.gad.epidemicmanage.pojo.entity.UserDetail;
import com.gad.epidemicmanage.service.IRoleService;
import com.gad.epidemicmanage.service.IUserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


/**
 * @author  guoandong
 * @date  2021/2/19 9:54
 * @desc 这是实现自定义用户认证的核心逻辑，
 * loadUserByUsername(String username)的参数就是登录时提交的用户名，
 * 返回类型是一个叫UserDetails 的接口，需要在这里构造出他的一个实现类User，这是Spring security提供的用户信息实体。
 *
 *
 **/
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    IUserService userService;

    @Resource
    IRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        if("".equals(username) || username == null){
            throw new UsernameNotFoundException("用户名不能为空!");
        }
        //手动注入
//        ApplicationContext context = ApplicationContextUtil.getContext();
//        IUserService userService = context.getBean(IUserService.class);
        //查询用户账号信息
        User curUser = userService.getOne(new LambdaQueryWrapper<User>()
            .eq(User::getUserName,username)
        );

        if(curUser == null){
            throw new UsernameNotFoundException("该账号不存在！");
        }
//        IRoleService roleService = context.getBean(IRoleService.class);
        //查询用户角色
        Role curRole = roleService.getOne(new LambdaQueryWrapper<Role>()
            .eq(Role::getUserId,curUser.getId())
        );
        authorities.add(new RoleDetail(curRole));

        return new UserDetail(curUser,new HashSet<>(authorities));
    }
}