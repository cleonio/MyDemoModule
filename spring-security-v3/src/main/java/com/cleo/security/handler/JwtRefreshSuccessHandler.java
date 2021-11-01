package com.cleo.security.handler;

import com.cleo.jwt.JWTService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @className: com.cleo.security.handler-> JwtRefreshSuccessHandler
 * @description:
 * @author: cleo
 * @createDate: 2021-10-29 13:53
 * @version: 1.0
 */
public class JwtRefreshSuccessHandler implements AuthenticationSuccessHandler {

    private static final int tokenRefreshInterval = 300;  //刷新间隔5分钟

    private JWTService jwtService;

    public JwtRefreshSuccessHandler() {
        this.jwtService = new JWTService();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
       /* DecodedJWT jwt = ((JwtAuthenticationToken)authentication).getPrincipal();
        boolean shouldRefresh = shouldTokenRefresh(jwt.getIssuedAt());
        if(shouldRefresh) {
            String newToken = jwtUserService.saveUserLoginInfo((UserDetails)authentication.getPrincipal());
            response.setHeader("Authorization", newToken);
        }*/

        Object principal = authentication.getPrincipal();
        System.out.println(principal);


    }

    protected boolean shouldTokenRefresh(Date issueAt){
        LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(tokenRefreshInterval).isAfter(issueTime);
    }
}
