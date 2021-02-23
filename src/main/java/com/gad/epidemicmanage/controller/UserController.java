package com.gad.epidemicmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gad.epidemicmanage.common.GlobalConstant;
import com.gad.epidemicmanage.pojo.vo.Result;
import com.gad.epidemicmanage.pojo.entity.User;
import com.gad.epidemicmanage.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    final private AuthenticationManager authenticationManager;

    /**
     * 登录操作
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("开始登录校验");
        Result result = new Result(true, "登录成功");
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

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
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public Result logout(HttpServletRequest req){
        log.info("开始注销");
        Result result = new Result(true,"注销成功");
        try{
            SecurityContextHolder.clearContext();
        }catch (Exception e){
            log.error("注销异常", e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("注销失败");
        }
        return result;
    }
}
