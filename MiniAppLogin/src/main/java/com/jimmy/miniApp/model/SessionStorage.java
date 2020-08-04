package com.jimmy.miniApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionStorage implements Serializable{

    private static final long serialVersionUID = -3665862907888791817L;
    /**
     * 主键
     */
    private Long id ;
    /**
     * 是否销毁 0-未销毁 1-已销毁
     */
    private Integer destroy ;
    /**
     * 10 公众号 customer 20 小程序 30 手机号码登录 relation
     */
    private Integer type ;
    /**
     * 创建人
     */
    private Long createBy ;
    /**
     * 微信小程序使用
     */
    private String sessionKey ;
    /**
     * 更新人
     */
    private Long updateBy ;
    /**
     * 用户id
     */
    private Long userId ;
    /**
     * 用户令牌
     */
    private String userToken ;
    /**
     * 创建时间
     */
    private Date createDate ;
    /**
     * 登录过期时间
     */
    private Date loginExpireTime ;
    /**
     * 登录时间
     */
    private Date loginTime ;
    /**
     * 更新时间
     */
    private Date updateTime ;
    /**
     * 删除标识：0-未删除，1-已删除
     */
    private Integer isDeleted ;
}
