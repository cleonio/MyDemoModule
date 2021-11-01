package com.cleo.security.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @className: com.cleo.security.handler-> HttpStatusLoginFailureHandler
 * @description: 登陆失败处理器
 * @author: cleo
 * @createDate: 2021-10-29 10:37
 * @version: 1.0
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        System.out.println("LoginFailureHandler error");
        //httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        String errorMsg = "";

        if (e instanceof LockedException) {
            errorMsg = "账户被锁定，请联系管理员!";
        } else if (e instanceof CredentialsExpiredException) {
            errorMsg = "密码过期，请联系管理员!";
        } else if (e instanceof AccountExpiredException) {
            errorMsg = "账户过期，请联系管理员!";
        } else if (e instanceof DisabledException) {
            errorMsg = "账户被禁用，请联系管理员!";
        } else if (e instanceof BadCredentialsException) {
            errorMsg = "用户名或者密码输入错误，请重新输入!";
        }else if (e instanceof AuthenticationCredentialsNotFoundException){
            errorMsg = "token is not found!";
        }else if (e instanceof InsufficientAuthenticationException){
            errorMsg = "token认证失败，请联系管理员!";
        }else if (e instanceof UsernameNotFoundException){
            errorMsg = "用户不存在，请联系管理员!";
        }

        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter out = httpServletResponse.getWriter();
        out.write(errorMsg);
        out.flush();
        out.close();

    }
}
