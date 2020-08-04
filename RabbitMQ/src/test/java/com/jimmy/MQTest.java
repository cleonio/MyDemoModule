package com.jimmy;

import com.jimmy.constant.MQConstant;
import com.jimmy.service.IMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiongyang
 * @date 2020/3/31 8:28
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MQTest {

    @Autowired
    private IMessageService messageService;

    @Test
    public void send() throws InterruptedException {
        System.out.println("发送时间:"+ System.currentTimeMillis());
        String message = "测试延迟消息";
        messageService.send(MQConstant.HELLO_QUEUE_NAME,message,6000);

        message = "测试普通消息";
        messageService.send(MQConstant.HELLO_QUEUE_NAME,message);

        message = "测试普通消息";
        messageService.send("hahaha",message);
    }

}
