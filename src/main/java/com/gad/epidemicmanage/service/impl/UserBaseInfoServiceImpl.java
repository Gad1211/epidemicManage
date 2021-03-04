package com.gad.epidemicmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gad.epidemicmanage.mapper.UserBaseInfoMapper;
import com.gad.epidemicmanage.pojo.entity.UserBaseInfo;
import com.gad.epidemicmanage.service.IUserBaseInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserBaseInfoServiceImpl extends ServiceImpl<UserBaseInfoMapper, UserBaseInfo> implements IUserBaseInfoService {
    @Override
    public void updateUserBaseInfo(UserBaseInfo userBaseInfo) {
        //获取原本用户信息
        UserBaseInfo userBaseInfoNew = getById(userBaseInfo.getId());
        //更新
        userBaseInfoNew.setName(userBaseInfo.getName());
        userBaseInfoNew.setCommunity(userBaseInfo.getCommunity());
        userBaseInfoNew.setEstate(userBaseInfo.getEstate());
        userBaseInfoNew.setHouseNumber(userBaseInfo.getHouseNumber());
        userBaseInfoNew.setIdCard(userBaseInfo.getIdCard());
        userBaseInfoNew.setGender(userBaseInfo.getGender());
        userBaseInfoNew.setAge(userBaseInfo.getAge());
        userBaseInfoNew.setPhone(userBaseInfo.getPhone());
        //更新到数据库
        updateById(userBaseInfoNew);
    }
}
