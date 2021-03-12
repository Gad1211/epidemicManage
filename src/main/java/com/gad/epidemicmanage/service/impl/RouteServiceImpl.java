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
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

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

    @Override
    public void exportRouteExcel(HttpServletResponse response) throws Exception{
        //获取所有通信录信息
        List<Route> list = list();

        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet("行程信息");
        sheet.setDefaultColumnWidth(15);
        //表头行创建
        HSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("用户ID");
        header.createCell(1).setCellValue("用户名");
        header.createCell(2).setCellValue("出发地");
        header.createCell(3).setCellValue("到达地");
        header.createCell(4).setCellValue("出发时间");
        header.createCell(5).setCellValue("到达时间");
        header.createCell(6).setCellValue("交通工具");
        header.createCell(7).setCellValue("座次");
        //数据写入单元格
        for (int i = 0; i < list.size(); i++) {
            Route route = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(route.getUserId());
            row.createCell(1).setCellValue(route.getUserName());
            row.createCell(2).setCellValue(route.getStartPlace());
            row.createCell(3).setCellValue(route.getEndPlace());
            row.createCell(4).setCellValue(route.getStartTime());
            row.createCell(5).setCellValue(route.getEndTime());
            row.createCell(6).setCellValue(route.getVehicle());
            row.createCell(7).setCellValue(route.getVehicleSeatNumber());
        }

        String fileName = "行程表.xls";

        fileName = URLEncoder.encode(fileName, "UTF-8");

        // 清空response
        response.reset();
        // 设置response的Header
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setCharacterEncoding("UTF-8");

        OutputStream outputStream = response.getOutputStream();
        book.write(outputStream);
    }
}
