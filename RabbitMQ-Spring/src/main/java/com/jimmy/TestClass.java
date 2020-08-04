package com.jimmy;

import com.jimmy.producer.MessageProducer;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiongyang
 * @date 2020/7/29 10:15
 * @Description:
 */
public class TestClass {

    private Logger logger = LoggerFactory.getLogger(TestClass.class);

    private ApplicationContext context = null;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void should_send_a_amq_message() throws Exception {
        MessageProducer messageProducer = (MessageProducer) context.getBean("messageProducer");
        int a = 100;
        while (a > 0) {
            //direct模式:消息与一个特定的路由器完全匹配，才会转发
            messageProducer.sendMessage1("mq.one.send","mq.one.send m1, I am amq sender num :" + a--);
            messageProducer.sendMessage1("mq.two.send","mq.two.send m1, I am amq sender num :" + a--);
            messageProducer.sendMessage1("mq.two.send.a","mq.two.send m2, I am amq sender num :" + a--);
            //topic模式:按规则转发消息，最灵活
            messageProducer.sendMessage2("mq.three.send","mq.three.send, I am amq sender num :" + a--);
            messageProducer.sendMessage2("mq.three.send1","mq.three.send1, I am amq sender num :" + a--);
            messageProducer.sendMessage2("mq.three.send.a","mq.three.send.a, I am amq sender num :" + a--);

            try {
                //暂停一下，好让消息消费者去取消息打印出来
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
