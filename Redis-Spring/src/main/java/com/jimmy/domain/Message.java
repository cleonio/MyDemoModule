package com.jimmy.domain;

import java.io.Serializable;

/**
 * @author xiongyang
 * @date 2020/7/29 10:39
 * @Description:
 */
public class Message implements Serializable {

    private Long id;

    private String msg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                '}';
    }
}
