package com.gad.epidemicmanage.config;

import com.gad.epidemicmanage.service.impl.UserDetailServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;


/**
 * @author  guoandong
 * @date  2021/2/18 14:42
 * @desc security配置类
 **/
//@Configuration
//@EnableWebSecurity
public class SecurityConfig{

//    @Override
//    public UserDetailsService userDetailsService() {
//        //获取用户账号密码及权限信息
//        return new UserDetailServiceImpl();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //配置认证方式
//        auth.userDetailsService(userDetailsService())
//                .passwordEncoder(new BCryptPasswordEncoder());;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//    }
//
//    /**
//     * 设置静态资源不被拦截
//     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/css/**","/js/**");
//    }

}
