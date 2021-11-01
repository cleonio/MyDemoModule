package com.cleo.security.handler;

import com.cleo.security.pojo.LoginUser;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @className: com.cleo.security.handler-> JwtAuthenticationToken
 * @description: JWT 认证数据
 * @author: cleo
 * @createDate: 2021-10-29 11:10
 * @version: 1.0
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private UserDetails principal;
    private String credentials;
    private Claims tokenClaims;

    public JwtAuthenticationToken(Claims tokenClaims) {
        super(Collections.emptyList());
        this.tokenClaims =  tokenClaims;
    }


    public JwtAuthenticationToken(UserDetails principal, Claims tokenClaims, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.tokenClaims = tokenClaims;
    }


    @Override
    public void setDetails(Object details) {
        super.setDetails(details);
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public Claims getTokenClaims() {
        return tokenClaims;
    }
}
