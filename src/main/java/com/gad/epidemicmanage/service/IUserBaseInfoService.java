package com.gad.epidemicmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gad.epidemicmanage.pojo.entity.UserBaseInfo;

public interface IUserBaseInfoService extends IService<UserBaseInfo> {
    /**
     * 更新用户基本信息
     */
    void updateUserBaseInfo(UserBaseInfo userBaseInfo);
}
