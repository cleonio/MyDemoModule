package com.jimmy;

import com.alibaba.fastjson.JSON;
import com.jimmy.constant.MQConstant;
import com.jimmy.domain.DLXMessage;
import com.jimmy.service.IMessageService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiongyang
 * @date 2020/3/31 10:18
 * @Description:
 */
//监听转发队列，有消息时，把消息转发到目标队列
@Component
@RabbitListener(queues = MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME)
public class ReceiverDelayMessage {

    @Autowired
    private IMessageService messageService;

    @RabbitHandler
    public void process(String content) {
        //此时，才把消息发送到指定队列，而实现延迟功能
        DLXMessage message = JSON.parseObject(content, DLXMessage.class);
        messageService.send(message.getQueueName(), message.getContent());
    }

}