package com.jimmy.miniApp.constant;


import java.beans.ConstructorProperties;

/**
 * @author xiongyang
 * @date 2020/3/2 11:22
 * @Description:
 */
public class RtnInfo<T> {
    public static final RtnInfo SUCCESS;
    public static final RtnInfo SERVER_ERROR;
    public static final RtnInfo SERVICE_UNAVAILABLE;
    public static final RtnInfo PARAM_MISSING;
    private String msg;
    private Integer code;
    private T data;
    private String count;

    public static RtnInfo success(Object data) {
        return builder().code(RtnConstant.Code.SUCCESS_CODE).msg("成功").data(data).build();
    }

    public static RtnInfo successTable(Object data, String count) {
        return builder().code(RtnConstant.Code.SUCCESS_CODE).msg("成功").data(data).count(count).build();
    }

    public static RtnInfo error(Integer code, String msg) {
        return builder().code(code).msg(msg).build();
    }

    public static <T> RtnInfo.RtnInfoBuilder<T> builder() {
        return new RtnInfo.RtnInfoBuilder();
    }

    public String getMsg() {
        return this.msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public String getCount() {
        return this.count;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RtnInfo)) {
            return false;
        } else {
            RtnInfo<?> other = (RtnInfo)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$msg = this.getMsg();
                    Object other$msg = other.getMsg();
                    if (this$msg == null) {
                        if (other$msg == null) {
                            break label59;
                        }
                    } else if (this$msg.equals(other$msg)) {
                        break label59;
                    }

                    return false;
                }

                Object this$code = this.getCode();
                Object other$code = other.getCode();
                if (this$code == null) {
                    if (other$code != null) {
                        return false;
                    }
                } else if (!this$code.equals(other$code)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                Object this$count = this.getCount();
                Object other$count = other.getCount();
                if (this$count == null) {
                    if (other$count != null) {
                        return false;
                    }
                } else if (!this$count.equals(other$count)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof RtnInfo;
    }

    @Override
    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        Object $msg = this.getMsg();
        result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        Object $count = this.getCount();
        result = result * 59 + ($count == null ? 43 : $count.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "RtnInfo(msg=" + this.getMsg() + ", code=" + this.getCode() + ", data=" + this.getData() + ", count=" + this.getCount() + ")";
    }

    @ConstructorProperties({"msg", "code", "data", "count"})
    public RtnInfo(String msg, Integer code, T data, String count) {
        this.msg = msg;
        this.code = code;
        this.data = data;
        this.count = count;
    }

    public RtnInfo() {
    }

    static {
        SUCCESS = builder().code(RtnConstant.Code.SUCCESS_CODE).msg("成功").build();
        SERVER_ERROR = builder().code(RtnConstant.Code.SERVER_ERROR_CODE).msg("服务器异常").build();
        SERVICE_UNAVAILABLE = builder().code(RtnConstant.Code.SERVICE_UNAVAILABLE_CODE).msg("当前服务不可用").build();
        PARAM_MISSING = builder().code(RtnConstant.Code.PARAMS_REEOR).msg("参数缺失").build();
    }

    public static class RtnInfoBuilder<T> {
        private String msg;
        private Integer code;
        private T data;
        private String count;

        RtnInfoBuilder() {
        }

        public RtnInfo.RtnInfoBuilder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public RtnInfo.RtnInfoBuilder<T> code(Integer code) {
            this.code = code;
            return this;
        }

        public RtnInfo.RtnInfoBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public RtnInfo.RtnInfoBuilder<T> count(String count) {
            this.count = count;
            return this;
        }

        public RtnInfo<T> build() {
            return new RtnInfo(this.msg, this.code, this.data, this.count);
        }

        @Override
        public String toString() {
            return "RtnInfo.RtnInfoBuilder(msg=" + this.msg + ", code=" + this.code + ", data=" + this.data + ", count=" + this.count + ")";
        }
    }
}
