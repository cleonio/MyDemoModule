package com.cleo.security.service;

import com.cleo.jwt.JWTService;
import com.cleo.security.pojo.LoginUser;
import com.cleo.security.pojo.LoginUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @className: com.cleo.security.handler-> CustomUserDetailsServiceHandler
 * @description: 自定义认证处理（）
 * 1.判断用户是否存在
 * 2.查询用户
 *
 * @author: cleo
 * @createDate: 2021-10-28 15:41
 * @version: 1.0
 */

public class CustomUserDetailsService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    public CustomUserDetailsService() {
        //this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();  //默认使用 bcrypt， strength=10
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    JWTService jwtService;



    @Override
    public LoginUser loadUserByUsername(String username){
        //模拟查询数据库
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername("jack");
        String encryptPwd = passwordEncoder.encode("123456");
        System.out.println(encryptPwd);
        loginUser.setPassword(encryptPwd);
        loginUser.setAccountNonExpire(true);
        loginUser.setEnable(true);
        loginUser.setAccountNonLock(true);
        loginUser.setCredentialsNonExpire(true);
        loginUser.setRoleIdList(Arrays.asList(1L,3L));
        return loginUser;

    }

    public void createUser(String username, String password){
        String encryptPwd = passwordEncoder.encode(password);
        /**
         * @todo 保存用户名和加密后密码到数据库
         */
    }

    public void deleteUserLoginInfo(String username) {
        /**
         * @todo 清除数据库或者缓存中登录salt
         */
    }

}
