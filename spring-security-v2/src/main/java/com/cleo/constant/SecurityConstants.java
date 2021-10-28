package com.cleo.constant;

import java.util.UUID;

/**
 * @className: com.cleo.constant-> SecurityConstants
 * @description:
 * @author: cleo
 * @createDate: 2021-10-28 16:27
 * @version: 1.0
 */
public class SecurityConstants {

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_TOKEN_NAME = "accessToken";

    public static final String JWT_SECRET = "nicaicai ";

    public static final String JWT_ID = UUID.randomUUID().toString();

    public static final String JWT_ISSUER = "cleo";

    public static final Long JWT_TTL = 60*60*1000L;  //millisecond

}
