package com.gad.epidemicmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gad.epidemicmanage.common.GlobalConstant;
import com.gad.epidemicmanage.common.utils.BCryptPasswordEncoderUtils;
import com.gad.epidemicmanage.mapper.UserMapper;
import com.gad.epidemicmanage.pojo.dto.UpdatePasswdDto;
import com.gad.epidemicmanage.pojo.dto.UserListDto;
import com.gad.epidemicmanage.pojo.dto.UserRigisterDto;
import com.gad.epidemicmanage.pojo.entity.Role;
import com.gad.epidemicmanage.pojo.entity.States;
import com.gad.epidemicmanage.pojo.entity.User;
import com.gad.epidemicmanage.pojo.entity.UserBaseInfo;
import com.gad.epidemicmanage.service.IRoleService;
import com.gad.epidemicmanage.service.IStatesService;
import com.gad.epidemicmanage.service.IUserBaseInfoService;
import com.gad.epidemicmanage.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    IUserBaseInfoService userBaseInfoService;
    @Resource
    IRoleService roleService;
    @Resource
    IStatesService statesService;


    @Override
    public int insertUser(UserRigisterDto userRigisterDto){
        //校验两次输入密码是否一致
        if(!userRigisterDto.getUserAgainPasaword().equals(userRigisterDto.getUserPassword())){
            log.info("两次输入密码不一致！");
            return 2;
        }
        //查询所有用户，避免用户名重复
        List<User> users = list();
        for(User curUser:users){
            if(curUser.getUserName().equals(userRigisterDto.getUserName())){
                log.info("该用户名已被使用");
                return GlobalConstant.STATE_FALSE;
            }
        }
        User user = new User();
        user.setUserName(userRigisterDto.getUserName());
        //加密密码存入数据库，不然无法存入
        user.setUserPassword(BCryptPasswordEncoderUtils.encodePassword(userRigisterDto.getUserPassword()));
        save(user);
        log.info("注册成功，开始保存角色信息");

        User curUser = getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName,user.getUserName()));
        //存入角色，默认为普通用户
        Role role = new Role();
        role.setUserId(curUser.getId());
        role.setRole(GlobalConstant.ROLE_USER);
        roleService.save(role);
        log.info("角色信息保存完成");
        return GlobalConstant.STATE_TRUE;
    }

    @Override
    public IPage<User> queryUsers(UserListDto userListDto) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        Page<User> page = new Page<>(userListDto.getCurrentPage(), userListDto.getPageSize());

        //若用户名不为空则模糊查询
        if(!userListDto.getUserName().isEmpty()){
            queryWrapper.like(User::getUserName,userListDto.getUserName());
        }

        return page(page,queryWrapper);
    }

    @Override
    public Integer updateUser(UpdatePasswdDto updatePasswdDto) {
        //获取原本用户
        User userNew = getById(updatePasswdDto.getUserId());

        //校验原密码
        if(!BCryptPasswordEncoderUtils.passWordMatch(updatePasswdDto.getOldPassword(),userNew.getUserPassword())){
            log.info("原密码错误，修改密码失败");
            return GlobalConstant.STATE_FALSE;
        }

        //校验再次输入
        if(!updatePasswdDto.getNewPassword().equals(updatePasswdDto.getNewRePssword())){
            log.info("修改密码，两次密码不一致");
            return 2;
        }

        //更新密码先加密密码
        userNew.setUserPassword(BCryptPasswordEncoderUtils.encodePassword(updatePasswdDto.getNewPassword()));
        //更新到数据库
        updateById(userNew);

        log.info("修改密码成功");
        return GlobalConstant.STATE_TRUE;
    }

    @Override
    public void deleteUser(Integer userId) {
        //删除用户表信息
        removeById(userId);
        log.info("删除用户表信息成功");
        //删除用户baseinfo表信息
        userBaseInfoService.remove(new LambdaQueryWrapper<UserBaseInfo>()
                .eq(UserBaseInfo::getUserId, userId));
        log.info("删除用户baseinfo表信息成功");
        //删除用户角色信息
        roleService.remove(new LambdaQueryWrapper<Role>()
                .eq(Role::getUserId, userId));
        log.info("删除用户角色信息成功");
        //删除状态信息
        statesService.remove(new LambdaQueryWrapper<States>()
                .eq(States::getUserId,userId));
        log.info("删除用户states表成功");
    }
}
