package com.cleo.security.config;

import com.cleo.constant.ExcludeAuthUri;
import com.cleo.jwt.JWTService;
import com.cleo.security.config.configurer.CusLoginConfigurer;
import com.cleo.security.config.configurer.JwtLoginConfigurer;
import com.cleo.security.fiter.JwtAuthenticationTokenFilter;
import com.cleo.security.fiter.OptionRequestFilter;
import com.cleo.security.handler.*;
import com.cleo.security.service.CustomJwtAuthenticationProvider;
import com.cleo.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * @className: com.cleo.security.config-> SecurityConfig
 * @description:
 * @author: cleo
 * @createDate: 2021-10-28 16:15
 * @version: 1.0
 */
@Configuration
@EnableWebSecurity //Spring Security 4.0开始
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    CustomUserDetailsService customUserDetailsService;
    LoginSuccessHandler loginSuccessHandler;
    TokenClearLogoutHandler tokenClearLogoutHandler;
    JwtRefreshSuccessHandler jwtRefreshSuccessHandler;

    @Autowired
    public SecurityConfig() {
        this.loginSuccessHandler = new LoginSuccessHandler(new JWTService());
        this.tokenClearLogoutHandler = new TokenClearLogoutHandler();
        this.customUserDetailsService = new CustomUserDetailsService();
        this.jwtRefreshSuccessHandler = new JwtRefreshSuccessHandler();
    }



    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        // 使用 BCryptPasswordEncoder 密码编码器，该编码器会将随机产生的 salt 混入最终生成的密文中
        return new BCryptPasswordEncoder();
    }

    @Bean("daoAuthenticationProvider")
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }

    @Bean("jwtAuthenticationProvider")
    protected AuthenticationProvider jwtAuthenticationProvider() {
        return new CustomJwtAuthenticationProvider();
    }

    /**
     * 身份认证接口
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth
          .authenticationProvider(daoAuthenticationProvider())
          .authenticationProvider(jwtAuthenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    //忽略的拦截
                    .antMatchers(ExcludeAuthUri.excludeUrl).permitAll()
                    //默认其它的请求都需要授权
                    .anyRequest()
                    //.authenticated()
                    .access("@rbacPermissionHandler.hasPermission(request, authentication)")
                .and()
                    .exceptionHandling()
                    //.authenticationEntryPoint(customerAuthenticationEntryPointHandler)
                    .accessDeniedHandler(new CustomerAccessDeniedHandler())
                .and()
                    //CRSF禁用，因为不使用session
                    .csrf().disable()
                    //禁用session
                    .sessionManagement().disable()
                    //禁用form登录
                    .formLogin().disable()
                    //支持跨域
                    .cors()
                .and()
                    //添加header设置，支持跨域和ajax请求
                    .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
                    new Header("Access-control-Allow-Origin","*"),
                    //响应首部 Access-Control-Expose-Headers 列出了哪些首部可以作为响应的一部分暴露给外部
                    new Header("Access-Control-Expose-Headers","accessToken"))))
                .and()
                    //拦截OPTIONS请求，直接返回header
                    .addFilterAfter(new OptionRequestFilter(), CorsFilter.class)
                    //添加登录filter
                    .apply(new CusLoginConfigurer<>()).loginSuccessHandler(loginSuccessHandler)
                .and()
                    //添加token的filter
                    .apply(new JwtLoginConfigurer<>()).tokenValidSuccessHandler(jwtRefreshSuccessHandler).permissiveRequestUrls("/logout")
                .and()
                    //使用默认的logoutFilter
                    .logout()
                    //默认就是"/logout"
                    .logoutUrl("/user/logout")
                    //logout时业务处理handle
                    .addLogoutHandler(tokenClearLogoutHandler)
                    //logout成功时业务处理handle
                //logout成功后返回200
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()) ;


    }




}
