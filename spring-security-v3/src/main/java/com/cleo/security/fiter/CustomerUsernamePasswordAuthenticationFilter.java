package com.cleo.security.fiter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @className: com.cleo.security.fiter-> CustomerUsernamePasswordAuthenticationFilter
 * @description:
 * @author: cleo
 * @createDate: 2021-10-29 10:27
 * @version: 1.0
 */
public class CustomerUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public CustomerUsernamePasswordAuthenticationFilter() {
        //拦截url为 "/login" 的POST请求
        super(new AntPathRequestMatcher("/user/login", "POST"));
    }

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(getAuthenticationManager(), "authenticationManager must be specified");
        Assert.notNull(getSuccessHandler(), "AuthenticationSuccessHandler must be specified");
        Assert.notNull(getFailureHandler(), "AuthenticationFailureHandler must be specified");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String body = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
        String username = null, password = null;
        if(StringUtils.hasText(body)) {
            JSONObject jsonObj = JSON.parseObject(body);
            username = jsonObj.getString("username");
            password = jsonObj.getString("password");
        }
        if (username == null){
            username = "";
        }
        if (password == null){
            password = "";
        }
        username = username.trim();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
