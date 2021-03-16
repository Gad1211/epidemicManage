package com.gad.epidemicmanage.controller;

import com.gad.epidemicmanage.common.GlobalConstant;
import com.gad.epidemicmanage.pojo.vo.Result;
import com.gad.epidemicmanage.pojo.entity.User;
import com.gad.epidemicmanage.pojo.vo.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author  guoandong
 * @date  2021/2/18 10:26
 * @desc 登录注销controller
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class LoginController {

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
            try {
                // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
                authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPassword()));

                UserVo userVo = new UserVo();
                userVo.setUserName(authentication.getName());
                userVo.setAuthorities(authentication.getAuthorities());
                result.setData(userVo);
            }catch (BadCredentialsException e){
                result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
                result.setMessage("密码错误");
                log.info("密码错误");
                return result;
            } catch (UsernameNotFoundException e){
                result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
                //截取异常打印
                String str = e.toString();
                String str1=str.substring(0, str.indexOf(": "));
                String str2=str.substring(str1.length()+2);
                result.setMessage(str2);
                log.info(str2);
                return result;
            }
            // 如果没有设置自定义的策略，就采用MODE_THREADLOCAL模式
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("登录成功");
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
    public Result logout(){
        log.info("开始注销");
        Result result = new Result(true,"注销成功");
        try{
            SecurityContextHolder.clearContext();
            log.info("注销成功");
        }catch (Exception e){
            log.error("注销异常", e);
            result.setCode(GlobalConstant.REQUEST_ERROR_STATUS_CODE);
            result.setMessage("注销失败");
        }
        return result;
    }
}
