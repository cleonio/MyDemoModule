package com.jimmy.producer;

import com.jimmy.constant.MQConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author xiongyang
 * @date 2020/7/29 10:12
 * @Description:
 */
@Service
public class MessageProducer {

    private Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    /*@Resource(name="amqpTemplate")
    private AmqpTemplate amqpTemplate;

    @Resource(name="amqpTemplate2")
    private AmqpTemplate amqpTemplate2;*/


    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate2;


    public void sendMessage1(String routingKey, Object message) throws IOException {
        logger.info("to send message:{}", message);
        rabbitTemplate.convertAndSend(routingKey,message);

    }

    public void sendMessage2(String routingKey, Object message) throws IOException {
        logger.info("to send message:{}", message);
        rabbitTemplate2.convertAndSend(routingKey,message);

    }
}
