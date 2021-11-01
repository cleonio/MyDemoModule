package com.cleo.security.service;

import com.alibaba.fastjson.JSON;
import com.cleo.security.handler.JwtAuthenticationToken;
import com.cleo.security.pojo.LoginUser;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.NonceExpiredException;

import java.util.Calendar;

/**
 * @className: com.cleo.security.service-> CustomJwtAuthenticationProvider
 * @description:  jwt认证提供者
 * @author: cleo
 * @createDate: 2021-11-01 16:41
 * @version: 1.0
 */
public class CustomJwtAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Claims tokenClaims = ((JwtAuthenticationToken) authentication).getTokenClaims();
        if (tokenClaims.getExpiration().before(Calendar.getInstance().getTime())){
            throw new NonceExpiredException("Token expires");
        }
        String subject = tokenClaims.getSubject();
        /**
         * 1.  tokenClaimn 简单存储 ： 查询数据
         *
         * 2. tokenClaimn 存储多种信息 ： 转换
         */
        LoginUser loginUser = JSON.parseObject(subject, LoginUser.class);

        JwtAuthenticationToken token = new JwtAuthenticationToken(loginUser, tokenClaims, loginUser.getAuthorities());

        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }
}
