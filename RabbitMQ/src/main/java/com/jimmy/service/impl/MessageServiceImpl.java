package com.jimmy.service.impl;

import com.alibaba.fastjson.JSON;
import com.jimmy.domain.DLXMessage;
import com.jimmy.constant.MQConstant;
import com.jimmy.service.IMessageService;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiongyang
 * @date 2020/3/31 10:15
 * @Description:
 */
@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息到队列
     * @param queueName 队列名称
     * @param message 消息内容
     */
    @Override
    public void send(String queueName, String message) {
        rabbitTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE,queueName, message);
    }

    /**
     * 延迟发送消息到队列
     * @param queueName 队列名称
     * @param message 消息内容
     * @param times 延迟时间 单位毫秒
     */
    @Override
    public void send(String queueName, String message, long times) {
        //消息发送到死信队列上，当消息超时时，会发生到转发队列上，转发队列根据下面封装的queueName，把消息转发的指定队列上
        //发送前，把消息进行封装，转发时应转发到指定 queueName 队列上
        DLXMessage dlxMessage = new DLXMessage(MQConstant.DEFAULT_EXCHANGE,queueName,message,times);
        MessagePostProcessor processor = new MessagePostProcessor(){
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(times + "");
                return message;
            }
        };
        rabbitTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE,MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME, JSON.toJSONString(dlxMessage), processor);
    }
}
