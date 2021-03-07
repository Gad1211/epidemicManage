package com.gad.epidemicmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gad.epidemicmanage.common.GlobalConstant;
import com.gad.epidemicmanage.common.utils.CommonUtil;
import com.gad.epidemicmanage.mapper.OutRecordMapper;
import com.gad.epidemicmanage.pojo.dto.OutRecordDto;
import com.gad.epidemicmanage.pojo.entity.OutRecord;
import com.gad.epidemicmanage.pojo.entity.States;
import com.gad.epidemicmanage.pojo.entity.Temperature;
import com.gad.epidemicmanage.service.IOutRecordService;
import com.gad.epidemicmanage.service.IStatesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.gad.epidemicmanage.common.GlobalConstant.STATE_FALSE;
import static com.gad.epidemicmanage.common.GlobalConstant.STATE_TRUE;

@Service
public class OutRecordServiceImpl extends ServiceImpl<OutRecordMapper, OutRecord> implements IOutRecordService {
    @Resource
    IStatesService statesService;

    @Override
    public Integer insertOutRecord(OutRecord outRecord) {
        //获取用户状态
        States states = statesService.getOne(new LambdaQueryWrapper<States>()
            .eq(States::getUserId,outRecord.getUserId()));
        //若异常状态直接返回否
        if (states.getAbnormal() == STATE_TRUE || states.getHighRisk() == STATE_TRUE || states.getHomeQuarantineDay() != 0){
            return STATE_FALSE;
        }

        //外出记录主键 时间戳加用户id
        String id = CommonUtil.getRecordTimeStamp() + outRecord.getUserId();
        outRecord.setId(id);

        save(outRecord);
        return STATE_TRUE;
    }

    @Override
    public IPage<OutRecord> queryOutRecords(OutRecordDto outRecordDto) {
        LambdaQueryWrapper<OutRecord> queryWrapper = new LambdaQueryWrapper<>();
        Page<OutRecord> page = new Page<>(outRecordDto.getCurrentPage(),outRecordDto.getPageSize());

        //若有外出记录id则匹配id
        if(!outRecordDto.getId().isEmpty()){
            queryWrapper.eq(OutRecord::getId,outRecordDto.getId());
        }
        //有用户id则匹配用户id
        if(outRecordDto.getUserId() != null){
            queryWrapper.eq(OutRecord::getUserId,outRecordDto.getUserId());
        }
        //有日期则匹配日期
        if(!outRecordDto.getOutStartTime().isEmpty() && !outRecordDto.getOutBackTime().isEmpty()){
            //le 小于等于  gt  大于
            queryWrapper.le(OutRecord::getOutStartTime, outRecordDto.getOutBackTime() + " 23:59:59");
            queryWrapper.gt(OutRecord::getOutBackTime, outRecordDto.getOutStartTime() + " 00:00:00");
        }
        //日期降序
        queryWrapper.orderByDesc(OutRecord::getOutStartTime);

        return page(page,queryWrapper);
    }
}
