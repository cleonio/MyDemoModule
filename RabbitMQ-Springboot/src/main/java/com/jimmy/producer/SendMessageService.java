package com.jimmy.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiongyang
 * @date 2020/7/29 15:39
 * @Description:
 */
@Service
public class SendMessageService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendDirectMessage(String directExchange,String directRoutingKey,Object message){
        rabbitTemplate.convertAndSend(directExchange,directRoutingKey,message);
    }

    public void sendTopicMessage(String topicExchange,String topicRoutingKey,Object message){
        rabbitTemplate.convertAndSend(topicExchange,topicRoutingKey,message);
    }

    public void sendFanoutMessage(String topicExchange,Object message){
        rabbitTemplate.convertAndSend(topicExchange,null,message);
    }
}
