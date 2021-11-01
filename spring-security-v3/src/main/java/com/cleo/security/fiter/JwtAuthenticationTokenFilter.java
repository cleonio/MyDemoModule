package com.cleo.security.fiter;

import com.cleo.constant.ExcludeAuthUri;
import com.cleo.constant.SecurityConstants;
import com.cleo.jwt.JWTService;
import com.cleo.jwt.JwtUtil;
import com.cleo.security.handler.JwtAuthenticationToken;
import com.cleo.security.pojo.LoginUser;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @className: com.cleo.security.fiter-> JwtAuthenticationTokenFilter
 * @description: 校验token
 * @author: cleo
 * @createDate: 2021-10-28 16:21
 * @version: 1.0
 */

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private RequestMatcher requiresAuthenticationRequestMatcher;
    private AuthenticationManager authenticationManager;
    private List<RequestMatcher> permissiveRequestMatchers;


    private AuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
    private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

    private JWTService jwtService ;

    public JwtAuthenticationTokenFilter() {
        this.requiresAuthenticationRequestMatcher = new RequestHeaderRequestMatcher(SecurityConstants.HEADER_TOKEN_NAME);
        this.jwtService = new JWTService();

    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        boolean ignoreFlag = Boolean.FALSE;

        /**
         * head 里面有没有 accessToken 字段
         *
         * 没有 ：判断是否是白名单url ; 是：跳过； 否的:花需要验证accessToken
         *
         *
         */
        if (!requiresAuthenticationRequestMatcher.matches(request)){

            for (String ignore : ExcludeAuthUri.excludeUrl) {
                if (request.getRequestURI().contains(ignore)){
                    ignoreFlag = Boolean.TRUE;
                    break;
                }
            }
            //filterChain.doFilter(request, response);
        }

        if (!ignoreFlag){
            Authentication authResult = null;
            AuthenticationException failed = null;

            String token = jwtService.getTokenFromRequest(request);
            try {
                if (StringUtils.isNotBlank(token)){
                    //LoginUser loginUser = jwtService.queryLoginUser(token);
                    //解密token
                    Claims claims = JwtUtil.parseJWT(token);
                    JwtAuthenticationToken authToken = new JwtAuthenticationToken(claims);
                    //验证 ：通过 CustomJwtAuthenticationProvider验证
                    authResult = this.getAuthenticationManager().authenticate(authToken);

                }else {
                    failed = new AuthenticationCredentialsNotFoundException("JWT is Empty");
                }
            } catch (Exception e) {
                failed =  new InsufficientAuthenticationException("token认证失败");
            }

            if (authResult != null){
                SecurityContextHolder.getContext().setAuthentication(authResult);
                successHandler.onAuthenticationSuccess(request, response, authResult);
            }else {
                SecurityContextHolder.clearContext();
                failureHandler.onAuthenticationFailure(request, response, failed);
            }
        }
        filterChain.doFilter(request, response);
    }

    protected AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    public void setAuthenticationFailureHandler(
            AuthenticationFailureHandler failureHandler) {
        Assert.notNull(failureHandler, "failureHandler cannot be null");
        this.failureHandler = failureHandler;
    }

    public void setAuthenticationSuccessHandler(
            AuthenticationSuccessHandler successHandler) {
        Assert.notNull(successHandler, "successHandler cannot be null");
        this.successHandler = successHandler;
    }

    public void setPermissiveUrl(String... urls) {
        if(permissiveRequestMatchers == null){
            permissiveRequestMatchers = new ArrayList<>();
        }
        for(String url : urls){
            permissiveRequestMatchers .add(new AntPathRequestMatcher(url));
        }
    }

}
