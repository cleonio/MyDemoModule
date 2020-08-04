package com.jimmy.miniApp.constant;

/**
 * Created by liben on 2018/7/3.
 */
public interface GusConstant {
    int NOT_DELETE = 0;//未删
    int DELETED = 1;//已删

    String ACCESS_TOKEN_KEY = "access_token_%s";//token缓存的key,占位符为code

    int WX_CONFIG_TYPE_PUBLIC = 10 ;  //微信配置类型-公众号
    int WX_CONFIG_TYPE_MINI = 20 ;    //微信配置类型-小程序

    int SESSION_STORAGE_TYPE_PUBLIC = 10 ;  //session的类型-公众号登录
    int SESSION_STORAGE_TYPE_MINI = 20 ;    //session的类型-小程序登录
    int SESSION_STORAGE_TYPE_PHONE = 30 ;    //session的类型-手机号登录
    int SESSION_STORAGE_TYPE_MINI_ALIPAY = 40 ;    //session的类型-支付宝小程序

    String HEADER_INVOKEPOOLCODE_KEY = "invokepoolcode"; // header pool code key
    String HEADER_TOKEN_KEY = "token"; // header token key
    String HEADER_TIMESTAMP_KEY = "timestamp"; // 时间戳 key

    int USER_INFO_LEVEL_CUSTOMER = 1 ;  //游客用户级别
    int USER_INFO_LEVEL_RELATION = 2 ;  //手机用户级别
    int USER_INFO_LEVEL_USER = 3;       //身份证用户级别

    int CODE_LENGTH_FOUR = 4 ;  //验证码长度:4

    String REDIS_BIND_MOBILE_KEY = "REDIS_BIND_MOBILE_KEY_%s" ; //缓存key:绑定手机号
    String REDIS_UNBIND_MOBILE_KEY = "REDIS_UNBIND_MOBILE_KEY_%s" ; //缓存key:解绑手机号
    long REDIS_TIME_FIVE_MINUTE = 300L;  //缓存时间5分钟

    int USER_TYPE_WX = 10 ; //微信用户
    int USER_TYPE_PHONE = 30;  //手机用户

    String CX9Z_ACCESS_TOKEN_KEY = "CxjzWeixinAccessToken";


    //微信图片鉴黄url
    String MINI_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    String IMG_IMGSECCHECK_URL = "https://api.weixin.qq.com/wxa/img_sec_check";

    //微信文字鉴黄url
    String IMG_MSGSECCHECK_URL = "https://api.weixin.qq.com/wxa/msg_sec_check";



    //小程序id  密钥配置
    String MINI_APPID = "wx31254db7c5baee0f";
    String MINI_SECRET = "f6e5d5538c99296e394318012f1000e4";

}
