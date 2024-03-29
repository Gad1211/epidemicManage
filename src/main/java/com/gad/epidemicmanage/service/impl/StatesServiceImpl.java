package com.gad.epidemicmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gad.epidemicmanage.common.GlobalConstant;
import com.gad.epidemicmanage.mapper.StatesMapper;
import com.gad.epidemicmanage.pojo.dto.BadStatesDto;
import com.gad.epidemicmanage.pojo.entity.States;
import com.gad.epidemicmanage.pojo.entity.User;
import com.gad.epidemicmanage.service.IStatesService;
import com.gad.epidemicmanage.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.gad.epidemicmanage.common.GlobalConstant.STATE_FALSE;
import static com.gad.epidemicmanage.common.GlobalConstant.STATE_TRUE;

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

    @Override
    public String queryStates(Integer id) {

        User user = userService.getById(id);
        if("admin".equals(user.getUserName())){
            return "管理员";
        }

        States states = getOne(new LambdaQueryWrapper<States>().eq(States::getUserId,id));

        if(states.getHighRisk() == STATE_TRUE && states.getAbnormal() == STATE_FALSE){
            return "高风险地区返回";
        }else if(states.getAbnormal() == STATE_TRUE && states.getHighRisk() == STATE_FALSE){
            return "体温异常";
        }else if(states.getHighRisk() == STATE_TRUE && states.getAbnormal() == STATE_TRUE){
            return "高风险地区返回、体温异常";
        }

        return "状态正常";
    }

    @Override
    public void updateTempCondition(Integer userId, Integer state) {
        States states = getOne(new LambdaQueryWrapper<States>()
                .eq(States::getUserId,userId));
        states.setAbnormal(state);

        updateById(states);
    }

    @Override
    public IPage<States> getBadStates(BadStatesDto badStatesDto) {

        LambdaQueryWrapper<States> queryWrapper = new LambdaQueryWrapper<>();
        Page<States> page = new Page<>(badStatesDto.getCurrentPage(),badStatesDto.getPageSize());
        //返回所有异常用户
        if(badStatesDto.getType() == 0){
            queryWrapper.eq(States::getAbnormal,STATE_TRUE)
                    .or()
            .eq(States::getHighRisk,STATE_TRUE);
            return page(page,queryWrapper);
            //返回体温异常用户
        }else if(badStatesDto.getType() == 1){
            queryWrapper.eq(States::getAbnormal,STATE_TRUE);
            return page(page,queryWrapper);
            //返回高风险地区返回用户
        }else if(badStatesDto.getType() == 2){
            queryWrapper.eq(States::getHighRisk,STATE_TRUE);
            return page(page,queryWrapper);
        }

        return null;
    }
}
