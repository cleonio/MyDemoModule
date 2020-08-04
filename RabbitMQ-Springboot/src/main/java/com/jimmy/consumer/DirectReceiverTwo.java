package com.jimmy.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xiongyang
 * @date 2020/7/29 15:49
 * @Description:
 */

@Component
@RabbitListener(queues = "MyDirectQueue")//监听的队列名称 TestDirectQueue
public class DirectReceiverTwo {

    @RabbitHandler
    public void process(Object testMessage) {
        System.out.println("DirectReceiverTwo消费者收到消息  : " + testMessage.toString());
    }
}
