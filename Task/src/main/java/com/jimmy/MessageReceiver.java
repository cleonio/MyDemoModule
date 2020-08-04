package com.jimmy;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiongyang
 * @date 2020/3/31 8:27
 * @Description:
 */
@Component
public class MessageReceiver {

    @RabbitListener(queues = "test_queue_1")
    public void receive(String id) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("消息接收时间:"+sdf.format(new Date()));
        System.out.println("接收到的消息:"+id);
    }
}
