package com.gad.epidemicmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gad.epidemicmanage.pojo.dto.BadStatesDto;
import com.gad.epidemicmanage.pojo.entity.States;

import java.util.List;

public interface IStatesService extends IService<States> {

    /**
     * 新增states数据
     *@param state1 身体
     *@param state2 地区
     *@param dayNum 天数
     */
    void insertCondition(Integer userId,Integer state1,Integer state2,Integer dayNum);

    /**
     * 修改是否身体异常和是否高风险地区返回
     *@param state1 对应 身体
     *@param state2 对应 地区
     */
    void updateCondition(Integer userId,Integer state1,Integer state2);

    /**
     * 只修改体温状态
     */
    void updateTempCondition(Integer userId,Integer state);

    /**
     * 修改居家隔离天数
     */
    void updateHomeQuarantineDay(Integer userId,Integer dayNum);

    /**
     * 查询状态
     */
    String queryStates(Integer id);

    /**
     * 查询异常状态用户列表
     * type0 全部异常用户
     * type1 体温异常用户
     * type2 高风险用户
     */
    IPage<States> getBadStates(BadStatesDto badStatesDto);
}
