package com.jimmy.service;

/**
 * @author xiongyang
 * @date 2020/3/31 10:14
 * @Description:
 */
public interface IMessageService {

    /**
     * 发送消息到队列
     * @param queueName 队列名称
     * @param message 消息内容
     */
    public void send(String queueName,String message);


    /**
     * 延迟发送消息到队列
     * @param queueName 队列名称
     * @param message 消息内容
     * @param times 延迟时间 单位毫秒
     */
    public void send(String queueName,String message,long times);
}
