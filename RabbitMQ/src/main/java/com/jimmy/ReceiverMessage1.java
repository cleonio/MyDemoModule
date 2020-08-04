package com.jimmy;

import com.jimmy.constant.MQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xiongyang
 * @date 2020/3/31 10:17
 * @Description:
 */
//监听hello队列，有消息时进行消费
@Component
@RabbitListener(queues = "hahaha")
public class ReceiverMessage1 {

    @RabbitHandler
    public void process(String content) {
        System.out.println("接受时间:"+ System.currentTimeMillis());
        System.out.println("接受消息:" + content);
    }
}

