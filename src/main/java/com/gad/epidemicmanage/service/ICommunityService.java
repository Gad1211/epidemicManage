package com.gad.epidemicmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gad.epidemicmanage.pojo.entity.Community;

public interface ICommunityService extends IService<Community> {

    /**
     * 删除社区
     */
    void deleteCommunity(Integer id);

}
