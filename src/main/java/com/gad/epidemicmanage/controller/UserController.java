package com.gad.epidemicmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gad.epidemicmanage.common.GlobalConstant;
import com.gad.epidemicmanage.pojo.vo.Result;
import com.gad.epidemicmanage.pojo.entity.User;
import com.gad.epidemicmanage.pojo.vo.LoginVo;
import com.gad.epidemicmanage.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author  guoandong
 * @date  2021/2/18 10:26
 * @desc 用户基础操作controller
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    /**
     * 登录操作
     */
    @GetMapping("/login")
    public Result login(@RequestBody User user, HttpServletRequest req){
        log.info("开始登录校验");
        Result result = new Result(true,"登录成功");

        try {
            //向数据库查询用户
            User curUser = userService.getOne(new LambdaQueryWrapper<User>()
            .eq(User::getUserName,user.getUserName())
            .eq(User::getUserPassword,user.getUserPassword())
            .eq(User::getRole,user.getRole())
            );

            //查询不为空
            if(curUser != null){
                log.info("登录校验成功");
                result.setCode(GlobalConstant.REQUEST_SUCCESS_STATUS_CODE);
                //设置展示对象
                LoginVo loginVo = new LoginVo();
                loginVo.setId(curUser.getId());
                loginVo.setUserName(curUser.getUserName());
                result.setData(loginVo);

                //设置session
                //参数为true时，若存在会话，则返回该会话，否则新建一个会话；
                //参数为false时，如存在会话，则返回该会话，否则返回NULL；
                HttpSession session = req.getSession(true);
                session.setAttribute("userName", curUser.getUserName());
                log.info("登录成功");
            }else {
                log.info("登录校验失败，输入信息错误");
                result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
                result.setMessage("登录失败，输入信息错误");
            }
        } catch (Exception e) {
            log.error("登录异常", e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("登录失败");
        }
        return result;
    }

    /**
     * 注销操作
     */
    @GetMapping("/logout")
    public Result logout(HttpServletRequest req){
        log.info("开始注销");
        Result result = new Result(true,"注销成功");
        try{
            //删除session
            req.getSession().invalidate();
            result.setMessage("注销成功");
            result.setCode(GlobalConstant.REQUEST_SUCCESS_STATUS_CODE);
            log.info("注销成功");
        }catch (Exception e){
            log.error("注销异常", e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("注销失败");
        }
        return result;
    }
}
