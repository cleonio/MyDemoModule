package com.jimmy.miniApp.enums;

public enum EnumComm {

    USER_TOKEN_ERROR(301,"用户认证失败"),

    SIGNATURE_NULL(401,"签名为空"),

    SIGNATURE_ERR(402,"签名错误"),

    INVOKE_POOL_NULL(403,"调用pool为空"),

    TIMESTAMP_NULL(404,"时间戳为空"),

    CODE_MISS(405,"验证码失效"),

    CODE_NOT_EQ_MISS(406,"验证码不正确");


    private EnumComm(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
