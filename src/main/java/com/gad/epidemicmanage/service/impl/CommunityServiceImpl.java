package com.gad.epidemicmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gad.epidemicmanage.mapper.CommunityMapper;
import com.gad.epidemicmanage.pojo.entity.Community;
import com.gad.epidemicmanage.pojo.entity.Estate;
import com.gad.epidemicmanage.service.ICommunityService;
import com.gad.epidemicmanage.service.IEstateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, Community> implements ICommunityService {

    @Resource
    IEstateService estateService;

    @Override
    public void deleteCommunity(Integer id) {
        removeById(id);
        log.info("删除社区，开始修改小区字段");
        //该社区下的小区中 修改所属社区id字段
        try{
            List<Estate> estateList = estateService.list(new LambdaQueryWrapper<Estate>().eq(Estate::getCommunityId,id));
            for(Estate estate:estateList){
                estate.setCommunityId(null);
            }
            estateService.updateBatchById(estateList);
            log.info("小区字段更新成功");
        }catch (Exception e){
            log.error("小区字段更新失败");
        }

    }
}
