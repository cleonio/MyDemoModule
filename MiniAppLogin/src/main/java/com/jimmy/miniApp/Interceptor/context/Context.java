package com.jimmy.miniApp.Interceptor.context;

import com.jimmy.miniApp.Interceptor.HttpHeader;
import com.jimmy.miniApp.model.SessionStorage;

/**
 * @author xiongyang
 * @date 2020/3/2 10:42
 * @Description:
 */
public class Context {

    /**
     * session context
     */
    private static ThreadLocal<SessionStorage> sessionContext = new ThreadLocal<>();

    /**
     * 存储header信息
     */
    private static ThreadLocal<HttpHeader> headerContext = new ThreadLocal<>();

    /**
     *  获取session
     * @return
     */
    public static SessionStorage getSession(){
        return sessionContext.get();
    }


    /**
     * 获取header头信息
     * @return
     */
    public static HttpHeader getHeader(){
        return headerContext.get();
    }

    /**
     * 放入session
     * @param sessionStorage
     */
    public static void putSession(SessionStorage sessionStorage){
        sessionContext.set(sessionStorage);
    }


    public static void putHeader(HttpHeader httpHeader){
        headerContext.set(httpHeader);
    }

    /**
     * 移除session
     */
    public static void removeSession(){
        sessionContext.remove();
    }


    public static void removeHeader(){
        headerContext.remove();
    }

}
