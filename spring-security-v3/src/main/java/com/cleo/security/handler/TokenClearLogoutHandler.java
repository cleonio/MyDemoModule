package com.cleo.security.handler;

import com.cleo.security.pojo.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className: com.cleo.security.handler-> TokenClearLogoutHandler
 * @description:
 * @author: cleo 退出成功处理器
 * @createDate: 2021-10-29 14:23
 * @version: 1.0
 */
public class TokenClearLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {

    }

    protected void clearToken(Authentication authentication) {
        if(authentication == null){

            return;
        }
        LoginUser user = (LoginUser)authentication.getPrincipal();
        if(user!=null && user.getUsername()!=null){

            //sout
            System.out.println("deleteUserLoginInfo ...");
        }
    }
}
