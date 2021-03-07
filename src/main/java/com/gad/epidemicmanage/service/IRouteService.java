package com.gad.epidemicmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gad.epidemicmanage.pojo.dto.RouteDto;
import com.gad.epidemicmanage.pojo.entity.Route;

public interface IRouteService extends IService<Route> {
    /**
     * 新增行程记录
     */
    void insertRoute(Route route);

    /**
     * 查询行程记录
     */
    IPage<Route> queryRoutes(RouteDto routeDto);
}
