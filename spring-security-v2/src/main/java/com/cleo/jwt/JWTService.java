package com.cleo.jwt;

import com.alibaba.fastjson.JSON;
import com.cleo.constant.SecurityConstants;
import com.cleo.security.pojo.LoginUser;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: com.cleo.jwt-> JWTService
 * @description:
 * @author: cleo
 * @createDate: 2021-10-28 16:24
 * @version: 1.0
 */

@Slf4j
@Component
public class JWTService {


    public String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_TOKEN_NAME);
        if (StringUtils.isEmpty(token)){
            token = request.getParameter(SecurityConstants.HEADER_TOKEN_NAME);
        }
        if (StringUtils.isNotEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)){
            token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
        }
        return token;
    }


    public String creatToken(Object loginUser){
        Map<String,Object> claims = new HashMap<>();
        try {
            String subject = JSON.toJSONString(loginUser);
            return createToken(claims,subject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String createToken(Map<String, Object> claims,String subject) throws Exception {
        return JwtUtil.createJWT(claims
                ,SecurityConstants.JWT_ISSUER
                ,subject
                , SecurityConstants.JWT_TTL);
    }



    public LoginUser queryLoginUser(HttpServletRequest request) throws Exception {
        String token = this.getTokenFromRequest(request);
        return queryLoginUser(token);
    }


    public LoginUser queryLoginUser(String token) throws Exception {
        if (StringUtils.isNotEmpty(token)){
            Claims claims = JwtUtil.parseJWT(token);
            if (claims!=null){
                String subject = claims.getSubject();
                if(StringUtils.isNotBlank(subject)){
                    return JSON.parseObject(subject, LoginUser.class);
                }
            }
        }
       throw new RuntimeException("parseJWT token error");

    }

}
