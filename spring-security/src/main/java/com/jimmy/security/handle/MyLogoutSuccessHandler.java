package com.jimmy.security.handle;

import com.alibaba.fastjson.JSON;
import com.base.entity.HttpStatus;
import com.base.entity.ResponseResult;
import com.base.utils.ServletUtils;
import com.base.utils.StringUtils;
import com.jimmy.common.Constants;
import com.jimmy.common.LoginUser;
import com.jimmy.manager.AsyncManager;
import com.jimmy.manager.factory.AsyncFactory;
import com.jimmy.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: jimmy.xiong
 * @Date: 2020/10/26 10:22
 * @Description:
 */
@Configuration
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {


    @Autowired
    private TokenService tokenService;

    /**
     *
     *退出处理
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(httpServletRequest);
        if (StringUtils.isNotNull(loginUser))
        {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(httpServletResponse, JSON.toJSONString(ResponseResult.error(HttpStatus.SUCCESS, "退出成功")));

    }
}
