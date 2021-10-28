package com.base.http;


import org.springframework.http.HttpStatus;

/**
 * @author Cleo
 * @description
 * @className HttpStatusException
 * @date 2020/11/5 9:36
 */

public class HttpStatusException extends Exception{

    private HttpStatus httpStatus;

    private String userMessage;

    private String developerMessage;

    public HttpStatusException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatusException(HttpStatus httpStatus, String userMessage, String developerMessage) {
        this.httpStatus = httpStatus;
        this.userMessage = userMessage;
        this.developerMessage = developerMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }
}
