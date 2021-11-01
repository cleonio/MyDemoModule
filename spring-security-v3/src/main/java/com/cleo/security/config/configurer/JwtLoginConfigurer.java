package com.cleo.security.config.configurer;

import com.cleo.security.fiter.JwtAuthenticationTokenFilter;
import com.cleo.security.handler.JwtAuthenticationFailureHandler;
import com.cleo.security.handler.LoginFailureHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * @className: com.cleo.security.config.configurer-> JwtLoginConfigurer
 * @description:
 * @author: cleo
 * @createDate: 2021-10-29 10:57
 * @version: 1.0
 */
public class JwtLoginConfigurer<T extends JwtLoginConfigurer<T, B>, B extends HttpSecurityBuilder<B>> extends AbstractHttpConfigurer<T, B> {

    private JwtAuthenticationTokenFilter authFilter;

    public JwtLoginConfigurer() {
        authFilter = new JwtAuthenticationTokenFilter();
    }

    @Override
    public void configure(B http) throws Exception {

        authFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

        //todo error
        authFilter.setAuthenticationFailureHandler(new JwtAuthenticationFailureHandler());

        JwtAuthenticationTokenFilter filter = postProcess(authFilter);
        http.addFilterBefore(filter, LogoutFilter.class);
    }

    public JwtLoginConfigurer<T, B> permissiveRequestUrls(String ... urls){
        authFilter.setPermissiveUrl(urls);
        return this;
    }

    public JwtLoginConfigurer<T, B> tokenValidSuccessHandler(AuthenticationSuccessHandler successHandler){
        authFilter.setAuthenticationSuccessHandler(successHandler);
        return this;
    }
}
