package com.cleo.security.config;

import com.cleo.constant.ExcludeAuthUri;
import com.cleo.security.fiter.JwtAuthenticationTokenFilter;
import com.cleo.security.handler.CustomUserDetailsServiceHandler;
import com.cleo.security.handler.CustomerAccessDeniedHandler;
import com.cleo.security.handler.CustomerAuthenticationEntryPointHandler;
import com.cleo.security.handler.CustomerLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

    @Autowired
    CustomUserDetailsServiceHandler customUserDetailsServiceHandler;

    @Autowired
    CustomerAuthenticationEntryPointHandler customerAuthenticationEntryPointHandler;

    @Autowired
    CustomerAccessDeniedHandler customerAccessDeniedHandler;

    @Autowired
    CustomerLogoutSuccessHandler customerLogoutSuccessHandler;

    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

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

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(customUserDetailsServiceHandler);
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }

    /**
     * 身份认证接口
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(authenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                    // CSRF禁用，因为不使用session
                    .csrf().disable()
                    // 认证失败处理类
                    .exceptionHandling()
                    .authenticationEntryPoint(customerAuthenticationEntryPointHandler)
                    .accessDeniedHandler(customerAccessDeniedHandler)
                .and()
                    // 基于token，所以不需要session
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    // 过滤请求
                    .authorizeRequests()
                    .antMatchers(ExcludeAuthUri.excludeUrl).permitAll()
                    // 除上面外的所有请求全部需要鉴权认证
                    .anyRequest()
                    //.access("@rbacPermissionService.hasPermission(request, authentication)")
                    .authenticated()

                .and()
                    .headers().frameOptions().disable();

        httpSecurity.logout().logoutUrl("/logOut").logoutSuccessHandler(customerLogoutSuccessHandler);

        // 添加JWT filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 添加CORS filter
        //httpSecurity.addFilterBefore(corsFilter, LogoutFilter.class);

    }


}
