package com.jimmy.miniApp.Interceptor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiongyang
 * @date 2020/3/2 10:34
 * @Description:
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpHeader {

    /**
     * 调用pool
     */
    private String invokePoolCode;

    /**
     * 调用时间戳
     */
    private Long timestamp;


    /**
     * 签名-必填
     */
    private String signature;


    /**
     * 用户标志
     */
    private String token;

    /**
     * 用户手机号
     */
    private String mobile;



    /**
     * 客户端调用ip
     */
    private String clientIp;
}
