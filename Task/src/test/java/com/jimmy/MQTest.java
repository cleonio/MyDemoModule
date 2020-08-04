package com.jimmy;

import com.jimmy.service.MessageService;
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
    private MessageService messageService;

    @Test
    public void send() throws InterruptedException {
        for (int i = 0; i <10 ; i++) {
            Thread.sleep(3000);
            messageService.sendMsg("test_queue_1",String.valueOf(i));
        }
    }

}
