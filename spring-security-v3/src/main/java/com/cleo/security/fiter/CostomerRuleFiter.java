package com.cleo.security.fiter;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.servlet.*;
import java.io.IOException;

/**
 * @className: com.cleo.security.fiter-> CostomerRuleFiter
 * @description:
 * @author: cleo
 * @createDate: 2021-10-29 17:12
 * @version: 1.0
 */
public class CostomerRuleFiter extends AbstractSecurityInterceptor implements Filter {
    @Override
    public Class<?> getSecureObjectClass() {
        return null;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }
}
