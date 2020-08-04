package com.jimmy.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiongyang
 * @date 2020/3/31 8:25
 * @Description:实现消息发送
 */
@Service
public class MessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String queueName,String msg) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("消息发送时间:"+sdf.format(new Date()));
        rabbitTemplate.convertAndSend("test_exchange", queueName, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //注意在发送的时候，必须加上一个header
                message.getMessageProperties().setHeader("x-delay",3000);
                return message;
            }
        });
    }
}
