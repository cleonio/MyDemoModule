package com.jimmy.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;

/**
 * @author xiongyang
 * @date 2020/7/28 15:35
 * @Description:
 */
public class ConsumerOne implements MessageListener {

    @Override
    public void onMessage(Message message) {
        MessageProperties messageProperties = message.getMessageProperties();
        String msg=  new String (message.getBody());
        System.out.println("ConsumerOne:"+msg);

    }
}
