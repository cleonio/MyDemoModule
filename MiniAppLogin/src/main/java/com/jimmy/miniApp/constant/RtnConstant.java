package com.jimmy.miniApp.constant;

/**
 * @author xiongyang
 * @date 2020/3/2 11:23
 * @Description:
 */
public class RtnConstant {

    public RtnConstant() {
    }

    public static class Msg {
        public static final String SUCCESS_MSG = "成功";
        public static final String SERVER_ERROR_MSG = "服务器异常";
        public static final String PARAMS_REEOR = "参数缺失";
        public static final String USER_NULL_MSG = "手机号未注册";
        public static final String VALIDCODE_ERROR_MSG = "验证码错误";
        public static final String VALIDCODE_TIMEOUT_CODE = "验证码失效";
        public static final String PASSWORD_ERROR_MSG = "密码错误";
        public static final String SERVICE_UNAVAILABLE_MSG = "当前服务不可用";

        public Msg() {
        }
    }

    public static class Code {
        public static final Integer SUCCESS_CODE = 0;
        public static final Integer PARAMS_REEOR = 992;
        public static final Integer SERVER_ERROR_CODE = 999;
        public static final Integer USER_NULL_CODE = 104;
        public static final Integer VALIDCODE_ERROR_CODE = 102;
        public static final Integer VALIDCODE_TIMEOUT_CODE = 103;
        public static final Integer PASSWORD_ERROR_CODE = 101;
        public static final Integer SERVICE_UNAVAILABLE_CODE = 405;

        public Code() {
        }
    }
}
