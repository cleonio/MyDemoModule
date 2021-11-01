package com.cleo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @className: com.cleo.security-> AuthenticationService
 * @description:
 * @author: cleo
 * @createDate: 2021-10-28 17:17
 * @version: 1.0
 */
@Component
public class AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;


    public Authentication authenticate(String username, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }


}
