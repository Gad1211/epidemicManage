package com.gad.epidemicmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gad.epidemicmanage.mapper.OutRecordMapper;
import com.gad.epidemicmanage.pojo.entity.OutRecord;
import com.gad.epidemicmanage.service.IOutRecordService;
import org.springframework.stereotype.Service;

@Service
public class OutRecordServiceImpl extends ServiceImpl<OutRecordMapper, OutRecord> implements IOutRecordService {
}
