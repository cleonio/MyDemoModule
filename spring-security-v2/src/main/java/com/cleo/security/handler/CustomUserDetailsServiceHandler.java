package com.cleo.security.handler;

import com.cleo.jwt.JWTService;
import com.cleo.security.pojo.LoginUser;
import com.cleo.security.pojo.LoginUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @className: com.cleo.security.handler-> CustomUserDetailsServiceHandler
 * @description: 自定义认证处理
 * 1.判断用户是否存在
 * 2.查询用户
 *
 * @author: cleo
 * @createDate: 2021-10-28 15:41
 * @version: 1.0
 */
@Component
public class CustomUserDetailsServiceHandler implements UserDetailsService {

    @Autowired
    JWTService jwtService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        //
        LoginUser loginUser = query();
        if (!loginUser.getUsername().equals(name)){
            throw new UsernameNotFoundException("用户不存在");
        }
        String token = jwtService.creatToken(loginUser);
        loginUser.setToken(token);
        return loginUser;
    }


    private LoginUser query(){
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername("jack");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePW = bCryptPasswordEncoder.encode("8888abc");
        loginUser.setPassword(encodePW);
        loginUser.setAccountNonExpire(true);
        loginUser.setEnable(true);
        loginUser.setAccountNonLock(true);
        loginUser.setCredentialsNonExpire(true);
        loginUser.setRoleIdList(Arrays.asList(1L,2L,3L));
        return loginUser;

    }
}
