package com.cleo.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @className: com.cleo.security.handler-> CustomerLogoutSuccessHandler
 * @description: 退出成功处理类
 * @author: cleo
 * @createDate: 2021-10-28 15:03
 * @version: 1.0
 */
@Component
public class CustomerLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        authentication.setAuthenticated(false);
        String msg = "退出成功";
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter out = httpServletResponse.getWriter();
        out.write(msg);
        out.flush();
        out.close();
    }
}
