package com.gad.epidemicmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gad.epidemicmanage.pojo.dto.UpdatePasswdDto;
import com.gad.epidemicmanage.pojo.dto.UserListDto;
import com.gad.epidemicmanage.pojo.dto.UserRigisterDto;
import com.gad.epidemicmanage.pojo.entity.User;

public interface IUserService extends IService<User> {

    /**
     * 注册用户
     */
    int insertUser(UserRigisterDto userRigisterDto);

    /**
     * 分页查询所有用户
     */
    IPage<User> queryUsers(UserListDto userListDto);

    /**
     * 修改用户密码
     */
    Integer updateUser(UpdatePasswdDto updatePasswdDto);

    /**
     * 删除用户信息
     */
    void deleteUser(Integer userId);
}
