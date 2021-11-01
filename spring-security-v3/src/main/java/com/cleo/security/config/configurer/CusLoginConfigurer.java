package com.cleo.security.config.configurer;

import com.cleo.security.fiter.CustomerUsernamePasswordAuthenticationFilter;
import com.cleo.security.handler.LoginFailureHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;

/**
 * @className: com.cleo.security.config.configurer-> JsonLoginConfigurer
 * @description: 对Filter做配置
 * @author: cleo
 * @createDate: 2021-10-29 10:24
 * @version: 1.0
 */
public class CusLoginConfigurer<T extends CusLoginConfigurer<T, B>, B extends HttpSecurityBuilder<B>> extends AbstractHttpConfigurer<T, B> {

    private CustomerUsernamePasswordAuthenticationFilter authFilter;

    public CusLoginConfigurer() {
        this.authFilter = new CustomerUsernamePasswordAuthenticationFilter();
    }


    @Override
    public void configure(B http) throws Exception {
        //设置Filter使用的AuthenticationManager,这里取公共的即可
        authFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        //设置失败的Handler
        authFilter.setAuthenticationFailureHandler(new LoginFailureHandler());
        //不将认证后的context放入session
        authFilter.setSessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy());

        CustomerUsernamePasswordAuthenticationFilter filter = postProcess(authFilter);
        //指定Filter的位置
        http.addFilterAfter(filter, LogoutFilter.class);
    }

    public CusLoginConfigurer<T,B> loginSuccessHandler(AuthenticationSuccessHandler authSuccessHandler){
        authFilter.setAuthenticationSuccessHandler(authSuccessHandler);
        return this;
    }
}
