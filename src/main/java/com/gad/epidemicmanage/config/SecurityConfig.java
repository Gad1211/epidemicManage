package com.gad.epidemicmanage.config;

import com.gad.epidemicmanage.service.impl.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author  guoandong
 * @date  2021/2/18 14:42
 * @desc security配置类
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public UserDetailServiceImpl userDetailsService() { return new UserDetailServiceImpl(); }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置认证方式
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //   不受认证: /login
                .authorizeRequests().antMatchers("/user/login").permitAll();

        http.httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .logout().disable();
    }

    @Override
    @Bean
    //注入authenticationManager
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
