package com.cleo.security.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @className: com.cleo.security.pojo-> LoginUser
 * @description:
 * @author: cleo
 * @createDate: 2021-10-28 15:46
 * @version: 1.0
 */
public class LoginUser implements UserDetails {

    private String username;

    private String password;

    private boolean accountNonExpire;

    private boolean accountNonLock;

    private boolean credentialsNonExpire;

    private boolean enable;

    private List<Long> roleIdList;

    private String token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*List<GrantedAuthority> authorities = new ArrayList<>();
        for (Long roleId : roleIdList) {
            authorities.add(new SimpleGrantedAuthority(String.valueOf(roleId)));
        }
        return authorities;*/


        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpire;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLock;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpire;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpire(boolean accountNonExpire) {
        this.accountNonExpire = accountNonExpire;
    }

    public void setAccountNonLock(boolean accountNonLock) {
        this.accountNonLock = accountNonLock;
    }

    public void setCredentialsNonExpire(boolean credentialsNonExpire) {
        this.credentialsNonExpire = credentialsNonExpire;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
