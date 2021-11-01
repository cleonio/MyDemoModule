package com.cleo.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @className: com.cleo.security.handler-> CustomerAccessDeniedHandler
 * @description: 授权异常处理类
 * @author: cleo
 * @createDate: 2021-10-28 15:00
 * @version: 1.0
 */
@Component
public class CustomerAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        String errormsg = "授权失败，请联系管理员!";
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter out = httpServletResponse.getWriter();
        out.write(new ObjectMapper().writeValueAsString(errormsg));
        out.flush();
        out.close();
    }
}
