package com.gad.epidemicmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gad.epidemicmanage.common.GlobalConstant;
import com.gad.epidemicmanage.mapper.UserBaseInfoMapper;
import com.gad.epidemicmanage.pojo.dto.UserBaseInfoDto;
import com.gad.epidemicmanage.pojo.entity.UserBaseInfo;
import com.gad.epidemicmanage.service.IStatesService;
import com.gad.epidemicmanage.service.IUserBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserBaseInfoServiceImpl extends ServiceImpl<UserBaseInfoMapper, UserBaseInfo> implements IUserBaseInfoService {
    @Resource
    IStatesService statesService;


    @Override
    public void insertUserBaseInfo(UserBaseInfoDto userBaseInfoDto) {
        //保存基础信息
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setUserId(userBaseInfoDto.getUserId());
        userBaseInfo.setName(userBaseInfoDto.getName());
        userBaseInfo.setCommunity(userBaseInfoDto.getCommunity());
        userBaseInfo.setEstate(userBaseInfoDto.getEstate());
        userBaseInfo.setHouseNumber(userBaseInfoDto.getHouseNumber());
        userBaseInfo.setIdCard(userBaseInfoDto.getIdCard());
        userBaseInfo.setGender(userBaseInfoDto.getGender());
        userBaseInfo.setAge(userBaseInfoDto.getAge());
        userBaseInfo.setPhone(userBaseInfoDto.getPhone());
        save(userBaseInfo);
        log.info("保存基础信息完成，开始保存states信息");

        //插入states表
        statesService.insertCondition(userBaseInfoDto.getUserId(),GlobalConstant.STATE_FALSE,GlobalConstant.STATE_FALSE,0);
        log.info("保存states信息完成");
    }

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
