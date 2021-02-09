package com.gad.epidemicmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gad.epidemicmanage.mapper.CommunityMapper;
import com.gad.epidemicmanage.pojo.entity.Community;
import com.gad.epidemicmanage.service.ICommunityService;
import org.springframework.stereotype.Service;

@Service
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, Community> implements ICommunityService {
}
