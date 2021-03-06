package com.gad.epidemicmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gad.epidemicmanage.common.utils.CommonUtil;
import com.gad.epidemicmanage.mapper.TemperatureMapper;
import com.gad.epidemicmanage.pojo.dto.TemperatureDto;
import com.gad.epidemicmanage.pojo.entity.Temperature;
import com.gad.epidemicmanage.pojo.entity.User;
import com.gad.epidemicmanage.service.ITemperatureService;
import com.gad.epidemicmanage.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TemperatureServiceImpl extends ServiceImpl<TemperatureMapper, Temperature> implements ITemperatureService {

    @Resource
    IUserService userService;

    @Override
    public void insertTemperature(Integer userId, Float temperatureNum) {
        Temperature temperature = new Temperature();
        temperature.setUserId(userId);
        temperature.setUserName(userService.getOne(new LambdaQueryWrapper<User>().eq(User::getId,userId)).getUserName());
        temperature.setTemperature(temperatureNum);
        temperature.setDate(CommonUtil.todayDate());

        save(temperature);
        //TODO 体温异常时，反馈数据给管理员,更新condition表
    }

    @Override
    public IPage<Temperature> queryTemperature(TemperatureDto temperatureDto) {
        LambdaQueryWrapper<Temperature> queryWrapper = new LambdaQueryWrapper<>();
        Page<Temperature> page = new Page<>(temperatureDto.getCurrentPage(), temperatureDto.getPageSize());

        //匹配指定用户体温记录
        queryWrapper.eq(Temperature::getUserId,temperatureDto.getUserId());

        //若日期不为空则查询指定日期
        if(!temperatureDto.getDate().isEmpty()){
            queryWrapper.eq(Temperature::getDate, CommonUtil.dateFormate(temperatureDto.getDate()));
        }

        //日期降序
        queryWrapper.orderByDesc(Temperature::getDate);

        return page(page,queryWrapper);
    }
}
