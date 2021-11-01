package com.cleo.security.handler;

import com.cleo.security.pojo.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: com.cleo.security.handler-> RbacPermissionHandler
 * @description: 自定义权限校验
 * @author: cleo
 * @createDate: 2021-11-01 11:02
 * @version: 1.0
 */
@Component
public class RbacPermissionHandler {

    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
        Object principal = authentication.getPrincipal();
        if (principal instanceof LoginUser){
            LoginUser loginUser = (LoginUser)authentication.getPrincipal();

            List<Long> list = loginUser.getRoleIdList();

            String requestURI = request.getRequestURI();

            List allowURL = queryRoleUrl(list);

            return allowURL.stream().anyMatch(f -> f.equals(requestURI));
        }
        return Boolean.FALSE;
    }


    public List queryRoleUrl( List<Long> roleIds){
        List<String> urlList = new ArrayList<>();

        for (Long roleId : roleIds) {
            if (roleId == 1L){
                urlList.add("/user/add");
            }
            if (roleId == 2L){
                urlList.add("/user/test");
            }
            if (roleId == 3L){
                urlList.add("/user/delete");
            }
        }
        return urlList;
    }
}
