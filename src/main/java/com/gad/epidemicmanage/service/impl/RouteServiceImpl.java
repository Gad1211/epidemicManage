package com.gad.epidemicmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gad.epidemicmanage.mapper.RouteMapper;
import com.gad.epidemicmanage.pojo.entity.Route;
import com.gad.epidemicmanage.service.IRouteService;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Route> implements IRouteService {
}
