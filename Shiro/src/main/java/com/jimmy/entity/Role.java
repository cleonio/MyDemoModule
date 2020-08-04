package com.jimmy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author xiongyang
 * @date 2020/5/8 21:35
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private String id;
    private String roleName;

    /**
     * 角色对应权限集合
     */
    private Set<Permissions> permissions;

}