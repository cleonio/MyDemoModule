package com.cleo.security.fiter;

import com.cleo.jwt.JWTService;
import com.cleo.security.pojo.LoginUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: com.cleo.security.fiter-> JwtAuthenticationTokenFilter
 * @description: 校验token
 * @author: cleo
 * @createDate: 2021-10-28 16:21
 * @version: 1.0
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        if(SecurityContextHolder.getContext().getAuthentication()!=null){

            System.out.println("111");//filterChain.doFilter(httpServletRequest, httpServletResponse);
        }else {
            String token = jwtService.getTokenFromRequest(httpServletRequest);
            if (StringUtils.isNotEmpty(token)){
                try {
                    LoginUser loginUser = jwtService.queryLoginUser(httpServletRequest);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,
                            loginUser.getPassword(), loginUser.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);


                } catch (Exception e) {
                    e.printStackTrace();
                    throw new InsufficientAuthenticationException("token认证失败");
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
