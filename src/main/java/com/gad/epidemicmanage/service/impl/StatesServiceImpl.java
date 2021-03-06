package com.gad.epidemicmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gad.epidemicmanage.mapper.StatesMapper;
import com.gad.epidemicmanage.pojo.entity.States;
import com.gad.epidemicmanage.pojo.entity.User;
import com.gad.epidemicmanage.service.IStatesService;
import com.gad.epidemicmanage.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StatesServiceImpl extends ServiceImpl<StatesMapper, States> implements IStatesService {
    @Resource
    IUserService userService;

    @Override
    public void insertCondition(Integer userId, Integer state1, Integer state2, Integer dayNum) {
        States states = new States();
        states.setUserId(userId);
        states.setUserName(userService.getOne(new LambdaQueryWrapper<User>().eq(User::getId,userId)).getUserName());
        states.setAbnormal(state1);
        states.setHighRisk(state2);
        states.setHomeQuarantineDay(dayNum);

        save(states);
    }

    @Override
    public void updateCondition(Integer userId,Integer state1, Integer state2) {
        States states = getOne(new LambdaQueryWrapper<States>()
                .eq(States::getUserId,userId));
        states.setAbnormal(state1);
        states.setHighRisk(state2);

        updateById(states);
    }

    @Override
    public void updateHomeQuarantineDay(Integer userId,Integer dayNum) {
        States states = getOne(new LambdaQueryWrapper<States>()
                .eq(States::getUserId,userId));
        states.setHomeQuarantineDay(dayNum);

        updateById(states);
    }
}
