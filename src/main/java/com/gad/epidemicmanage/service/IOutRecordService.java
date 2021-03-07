package com.gad.epidemicmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gad.epidemicmanage.pojo.dto.OutRecordDto;
import com.gad.epidemicmanage.pojo.entity.OutRecord;

public interface IOutRecordService extends IService<OutRecord> {

    /**
     * 新增外出记录
     */
    Integer insertOutRecord(OutRecord outRecord);

    /**
     * 查询外出记录
     * @param outRecordDto
     * @return
     */
    IPage<OutRecord> queryOutRecords(OutRecordDto outRecordDto);
}
