package com.cleo.security.handler;

import com.cleo.jwt.JWTService;
import com.cleo.security.pojo.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: com.cleo.security.handler-> LoginSuccessHandler
 * @description: 登陆成功处理器
 * @author: cleo
 * @createDate: 2021-10-29 10:45
 * @version: 1.0
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private JWTService jwtService;

    public LoginSuccessHandler(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
        String token = jwtService.creatToken(loginUser);
        //httpServletResponse.setHeader("aces", token);

    }
}
