package com.gad.epidemicmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gad.epidemicmanage.pojo.dto.UserBaseInfoDto;
import com.gad.epidemicmanage.pojo.entity.UserBaseInfo;

public interface IUserBaseInfoService extends IService<UserBaseInfo> {

    /**
     * 新增
     */
    void insertUserBaseInfo(UserBaseInfoDto userBaseInfoDto);

    /**
     * 更新用户基本信息
     */
    void updateUserBaseInfo(UserBaseInfo userBaseInfo);
}
