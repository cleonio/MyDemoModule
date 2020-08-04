package com.jimmy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author xiongyang
 * @date 2020/5/8 21:33
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;
    private String userName;
    private String password;

    /**
     * 用户对应的角色集合
     */
    private Set<Role> roles;
}
