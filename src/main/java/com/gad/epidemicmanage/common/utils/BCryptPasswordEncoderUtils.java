package com.gad.epidemicmanage.common.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author  guoandong
 * @date  2021/2/23 17:48
 * @desc  BCryptPassword加密工具
 **/
public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "222";
        String pwd = encodePassword(password);
        System.out.println(pwd);
    }
}