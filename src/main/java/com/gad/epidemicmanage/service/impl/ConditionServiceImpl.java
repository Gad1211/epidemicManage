package com.gad.epidemicmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gad.epidemicmanage.mapper.ConditionMapper;
import com.gad.epidemicmanage.pojo.entity.Condition;
import com.gad.epidemicmanage.service.IConditionService;
import org.springframework.stereotype.Service;

@Service
public class ConditionServiceImpl extends ServiceImpl<ConditionMapper, Condition> implements IConditionService {
}
