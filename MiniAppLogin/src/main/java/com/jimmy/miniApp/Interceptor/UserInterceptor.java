package com.jimmy.miniApp.Interceptor;

import com.base.utils.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimmy.miniApp.Interceptor.context.Context;
import com.jimmy.miniApp.annotation.LoginAnnotation;
import com.jimmy.miniApp.constant.GusConstant;
import com.jimmy.miniApp.constant.RtnInfo;
import com.jimmy.miniApp.model.SessionStorage;
import com.jimmy.miniApp.utils.SignatureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.jimmy.miniApp.enums.EnumComm.USER_TOKEN_ERROR;

/**
 * @author xiongyang
 * @date 2020/3/2 9:55
 * @Description:使用springMVC 拦截器/过滤器
 */

@Component
@Slf4j
public class UserInterceptor extends HandlerInterceptorAdapter {


    List<String> headerKeyList = new ArrayList<>();

    /**
     * 在业务处理器处理请求之前被调用，做安全控制
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("authenInterceptor preHandle...");
        if (handler instanceof HandlerMethod){
            if(!validateSign(request,response)){
                return false;
            }
            boolean needAuth = hasAnnotation(handler);
            SessionStorage sessionStorage = fetchSession(request);
            //接口需要认证&没有session 被拦截返回失败
            if (needAuth && sessionStorage == null) {
                Context.removeHeader();
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().write(new ObjectMapper().writeValueAsString(RtnInfo.error(USER_TOKEN_ERROR.getCode(),USER_TOKEN_ERROR.getMsg())));
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否有权限注解
     * @param handler
     * @return
     */
    private boolean hasAnnotation(Object handler) {
        HandlerMethod myHandlerMethod = (HandlerMethod) handler;
        return myHandlerMethod.getMethod().getAnnotation(LoginAnnotation.class) != null ||
                myHandlerMethod.getBean().getClass().getAnnotation(LoginAnnotation.class) != null;
    }

    private boolean validateSign(HttpServletRequest request, HttpServletResponse response){

        Map<String, String> param = extractParam(request);
        Context.putHeader(
                HttpHeader.builder().invokePoolCode(param.get(GusConstant.HEADER_INVOKEPOOLCODE_KEY))
                        .signature(param.get(SignatureUtil.signatureKey)).token(param.get(GusConstant.HEADER_TOKEN_KEY))
                        .clientIp(WebUtil.getClientIp(request))
                        .build());
        return true;

    }

    /**
     * @功能描述 抽取HttpServletRequest内参数
     * @应用场景
     * @author xiongyang
     * @date 2020/03/02 10:31
     * @params * @param null
     * @return
     */
    private Map<String, String> extractParam(HttpServletRequest request) {
        Map<String, String> bizPara = new HashMap();
        Enumeration<String> params = request.getParameterNames();
        if (params!=null){
            while ((params.hasMoreElements())){
                String key = params.nextElement();
                String value = request.getParameter(key);
                bizPara.put(key,value);
            }
        }

        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames!=null){
            while (headerNames.hasMoreElements()){
                String key = headerNames.nextElement();
                String value = request.getHeader(key);
                if (!validateExpectHeader(key)){
                    continue;
                }
                bizPara.put(key,value);
            }
        }
        return bizPara;

    }

    /**
     * 校验是否为预期的key
     * @param key
     * @return
     */
    private boolean validateExpectHeader(String key) {
        return headerKeyList.contains(key.toLowerCase());
    }

    private SessionStorage fetchSession(HttpServletRequest request) {
        //这里获取请求头中的用户名信息进行比对，看是否是已经授权过的已登录用户
        String token = request.getHeader("token");
        log.error(request.getHeader("Host"));
        if (token != null) {
            //如果clientSessionKey不为空，说明查找到了用户登录状态

            SessionStorage sessionStorage = null;
            //SessionStorage sessionStorage = sessionStorageService.findByToken(token);
            if (sessionStorage != null) {
                Context.putSession(sessionStorage);

            }
            return sessionStorage;
        }
        return null;
    }


}
