package com.gad.epidemicmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gad.epidemicmanage.common.GlobalConstant;
import com.gad.epidemicmanage.common.utils.BCryptPasswordEncoderUtils;
import com.gad.epidemicmanage.pojo.dto.UserListDto;
import com.gad.epidemicmanage.pojo.entity.Role;
import com.gad.epidemicmanage.pojo.entity.User;
import com.gad.epidemicmanage.pojo.entity.UserBaseInfo;
import com.gad.epidemicmanage.pojo.vo.Result;
import com.gad.epidemicmanage.service.IRoleService;
import com.gad.epidemicmanage.service.IUserBaseInfoService;
import com.gad.epidemicmanage.service.IUserService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author  guoandong
 * @date  2021/3/4 13:56
 * @desc 用户操作controller
 **/

@Slf4j
@RestController
public class UserController {

    @Resource
    IUserService userService;

    @Resource
    IUserBaseInfoService userBaseInfoService;

    @Resource
    IRoleService roleService;

    /**
     * 账户注册
     */
    @PostMapping("/register")
    public Result accountRegister(@RequestBody User user){
        log.info("开始注册");
        Result result = new Result(true, "注册成功");
        try{
            //加密密码存入数据库，不然无法存入
            user.setUserPassword(BCryptPasswordEncoderUtils.encodePassword(user.getUserPassword()));
            userService.save(user);
            log.info("注册成功");
        }catch (Exception e){
            log.error("注册失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("注册失败");
        }
        return result;
    }

    /**
     * 条件分页查询所有用户
     */
    @GetMapping("/queryUsers")
    public Result queryUsers(@RequestBody UserListDto userListDto){
        log.info("查询所有用户开始");
        Result result = new Result(true, "查询所有用户成功");
        try{
            IPage<User> userPage = userService.queryUsers(userListDto);
            result.setData(userPage);
            log.info("查询所有用户成功");
        }catch (Exception e){
            log.error("查询所有用户失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("查询所有用户失败");
        }
        return result;
    }

    /**
     * 修改用户账号信息
     */
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        log.info("修改用户信息开始");
        Result result = new Result(true, "修改用户信息成功");
        try{
            userService.updateUser(user);
            log.info("修改用户信息成功");
        }catch (Exception e){
            log.error("修改用户信息失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("修改用户信息失败");
        }
        return result;
    }

    /**
     * 删除用户,该操作会同时删除用户的详细信息和角色
     */
    @Transactional
    @PostMapping("/deleteUser/{userId}")
    public Result deleteUser(@PathVariable Integer userId){
        log.info("开始删除用户");
        Result result = new Result(true, "删除用户成功");
        //设置回滚点
        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        try{
            userService.deleteUser(userId);
            log.info("删除用户成功");
        }catch (Exception e){
            //回滚事务
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
            log.error("删除用户失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("删除用户失败");
        }
        return result;
    }

    /**
     * 记录用户详细信息
     */
    @PostMapping("/insertBaseInfo")
    public Result recordBaseInfo(@RequestBody UserBaseInfo userBaseInfo){
        log.info("开始记录用户详细信息");
        Result result = new Result(true, "用户详细信息记录成功");
        try{
            userBaseInfoService.save(userBaseInfo);
            log.info("用户详细信息记录成功");
        }catch (Exception e){
            log.error("注册失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("用户详细信息记录失败");
        }
        return result;
    }

    /**
     * 查询用户信息
     */
    @GetMapping("/queryBaseInfo/{userId}")
    public Result queryBaseInfo(@PathVariable Integer userId){
        log.info("开始查询用户详细信息");
        Result result = new Result(true, "查询用户详细信息成功");
        try{
            UserBaseInfo userBaseInfo = userBaseInfoService.getOne(new LambdaQueryWrapper<UserBaseInfo>()
                    .eq(UserBaseInfo::getUserId,userId));
            result.setData(userBaseInfo);
            log.info("查询用户详细信息成功");
        }catch (Exception e){
            log.error("查询用户详细信息失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("查询用户详细信息失败");
        }
        return result;
    }

    /**
     * 修改用户详细信息
     * @return
     */
    @PostMapping("/updateUserBaseInfo")
    public Result updateUserBaseInfo(@RequestBody UserBaseInfo userBaseInfo){
        log.info("修改用户详细信息开始");
        Result result = new Result(true, "修改用户详细信息成功");
        try{
            userBaseInfoService.updateUserBaseInfo(userBaseInfo);
            log.info("修改用户详细信息成功");
        }catch (Exception e){
            log.error("修改用户详细信息失败："+e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("修改用户详细信息失败");
        }
        return result;
    }


}
