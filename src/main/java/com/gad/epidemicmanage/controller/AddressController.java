package com.gad.epidemicmanage.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gad.epidemicmanage.common.GlobalConstant;
import com.gad.epidemicmanage.pojo.entity.Community;
import com.gad.epidemicmanage.pojo.entity.Estate;
import com.gad.epidemicmanage.pojo.vo.Result;
import com.gad.epidemicmanage.service.ICommunityService;
import com.gad.epidemicmanage.service.IEstateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author  guoandong
 * @date  2021/3/10 14:01
 * @desc  社区和小区地址controller
 **/

@Slf4j
@RestController
public class AddressController {

    @Resource
    ICommunityService communityService;

    @Resource
    IEstateService estateService;

    /**
     * 查询所有社区
     */
    @GetMapping("/queryAllCommunity")
    public Result queryAllCommunity(){
       log.info("开始查询所有社区");
       Result result = new Result(true,"查询所有社区信息成功");
       try{
           List<Community> res = communityService.list();
           result.setData(res);
           log.info("查询所有社区信息成功");
       }catch (Exception e){
           log.error("查询所有社区信息失败："+e);
           result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
           result.setMessage("查询所有社区信息失败");
       }
       return result;
    }

    /**
     * 修改社区信息
     */
    @PostMapping("/updateCommunity")
    public Result updateCommunity(@RequestBody Community community){
        log.info("开始修改社区");
        Result result = new Result(true,"修改社区成功");
        try{
            communityService.updateById(community);
            log.info("修改社区成功");
        }catch (Exception e){
            log.error("修改社区失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("修改社区失败");
        }
        return result;
    }

    /**
     * 删除社区,此操作并不会删除所属社区的小区,只会删除小区所属社区的id
     */
    @PostMapping("/deleteCommunity/{id}")
    public Result deleteCommunityById(@PathVariable Integer id){
        log.info("开始删除社区");
        Result result = new Result(true,"删除社区成功");
        try{
            communityService.deleteCommunity(id);
            log.info("删除社区成功");
        }catch (Exception e){
            log.error("删除社区失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("删除社区失败");
        }
        return result;
    }

    /**
     * 新增社区
     */
    @PostMapping("/insertCommunity")
    public Result insertCommunity(@RequestBody Community community){
        log.info("开始新增社区");
        Result result = new Result(true,"新增社区成功");
        try{
            communityService.save(community);
            log.info("新增社区成功");
        }catch (Exception e){
            log.error("新增社区失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("新增社区失败");
        }
        return result;
    }


    /**
     * 查询所有小区
     */
    @GetMapping("/queryAllEstate")
    public Result queryAllEstate(){
        log.info("开始查询所有小区");
        Result result = new Result(true,"查询所有小区信息成功");
        try{
            List<Estate> res = estateService.list();
            result.setData(res);
            log.info("查询所有小区信息成功");
        }catch (Exception e){
            log.error("查询所有小区信息失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("查询所有小区信息失败");
        }
        return result;
    }

    /**
     * 根据社区ID查询小区
     */
    @GetMapping("/queryEstateByCommunityId/{id}")
    public Result queryEstateByCommunityId(@PathVariable Integer id){
        log.info("开始根据社区id查询小区");
        Result result = new Result(true,"根据社区id查询小区成功");
        try{
            List<Estate> res = estateService.list(new LambdaQueryWrapper<Estate>()
                .eq(Estate::getCommunityId,id));
            result.setData(res);
            log.info("根据社区id查询小区成功");
        }catch (Exception e){
            log.error("根据社区id查询小区失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("根据社区id查询小区失败");
        }
        return result;
    }

    /**
     * 修改小区信息
     */
    @PostMapping("/updateEstate")
    public Result updateEstate(@RequestBody Estate estate){
        log.info("开始修改小区");
        Result result = new Result(true,"修改小区成功");
        try{
            estateService.updateById(estate);
            log.info("修改小区成功");
        }catch (Exception e){
            log.error("修改小区失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("修改小区失败");
        }
        return result;
    }

    /**
     * 删除小区
     */
    @PostMapping("/deleteEstate/{id}")
    public Result deleteEstateById(@PathVariable Integer id){
        log.info("开始删除小区");
        Result result = new Result(true,"删除小区成功");
        try{
            estateService.removeById(id);
            log.info("删除小区成功");
        }catch (Exception e){
            log.error("删除小区失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("删除小区失败");
        }
        return result;
    }

    /**
     * 新增小区
     */
    @PostMapping("/insertEstate")
    public Result insertEstate(@RequestBody Estate estate){
        log.info("开始新增小区");
        Result result = new Result(true,"新增小区成功");
        try{
            estateService.save(estate);
            log.info("新增小区成功");
        }catch (Exception e){
            log.error("新增小区失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("新增小区失败");
        }
        return result;
    }
}
