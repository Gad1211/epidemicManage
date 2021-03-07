package com.gad.epidemicmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gad.epidemicmanage.mapper.RouteMapper;
import com.gad.epidemicmanage.pojo.dto.RouteDto;
import com.gad.epidemicmanage.pojo.entity.OutRecord;
import com.gad.epidemicmanage.pojo.entity.Route;
import com.gad.epidemicmanage.pojo.entity.User;
import com.gad.epidemicmanage.service.IRouteService;
import com.gad.epidemicmanage.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Route> implements IRouteService {

    @Resource
    IUserService userService;

    @Override
    public void insertRoute(Route route) {
        User user = userService.getOne(new LambdaQueryWrapper<User>()
                    .eq(User::getId,route.getUserId()));

        route.setUserName(user.getUserName());
        save(route);
    }

    @Override
    public IPage<Route> queryRoutes(RouteDto routeDto) {
        LambdaQueryWrapper<Route> queryWrapper = new LambdaQueryWrapper<>();
        Page<Route> page = new Page<>(routeDto.getCurrentPage(),routeDto.getPageSize());

        //根据id 查询
        if(routeDto.getUserId() != null){
            queryWrapper.eq(Route::getUserId,routeDto.getUserId());
        }
        //根据用户名查询
        if(!routeDto.getUserName().isEmpty()){
            queryWrapper.eq(Route::getUserName,routeDto.getUserName());
        }
        //根据出发地查询
        if(!routeDto.getStartPlace().isEmpty()){
            queryWrapper.eq(Route::getStartPlace,routeDto.getStartPlace());
        }
        //有日期则匹配日期
        if(!routeDto.getStartTime().isEmpty() && !routeDto.getEndTime().isEmpty()){
            //le 小于等于  gt  大于
            queryWrapper.le(Route::getStartTime, routeDto.getEndTime() + " 23:59:59");
            queryWrapper.gt(Route::getEndTime, routeDto.getStartTime() + " 00:00:00");
        }
        //日期降序
        queryWrapper.orderByDesc(Route::getStartTime);
        return page(page,queryWrapper);
    }
}
