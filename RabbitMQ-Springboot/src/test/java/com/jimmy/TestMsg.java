package com.jimmy;

import com.jimmy.producer.SendMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiongyang
 * @date 2020/7/28 16:28
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMsg {

    @Autowired
    private SendMessageService sendMessageService;

    @Test
    public void test1(){
        int a = 100;
        while (a > 0) {
            sendMessageService.sendDirectMessage("MyDirectExchange","MyDirectRouting",
                    "mq.one.send m1, I am amq sender num :" + a--);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test2(){
        /*
         * key:topic.man
         * 通配topic.man和topic.#，会发到这两个队列里
         *
         * TopicManReceiver，TopicTotalReceiver这个队列都能收到消息
         *
         * */
        int a = 100;
        while (a > 0) {
            sendMessageService.sendTopicMessage("MyTopicExchange","topic.man",
                    "topic.man, I am amq sender num :" + a--);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test3(){
        /*
        * key:topic.woman
        * 只通配topic.#  会发这一个个队列里
        *
        * 只有TopicTotalReceiver这个队列才收到消息
        *
        * */
        int a = 100;
        while (a > 0) {
            sendMessageService.sendTopicMessage("MyTopicExchange","topic.woman",
                    "topic.woman, I am amq sender num :" + a--);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test4(){
        /*
         *  三个队列都绑定这个交换机，所以三个消息接收类都监听到了这条消息。
         *
         * */
        int a = 100;
        while (a > 0) {
            sendMessageService.sendFanoutMessage("MyfanoutExchange","MyfanoutExchange, I am amq sender num :" + a--);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test5(){
        /*
         *  消息确认触发回调函数
         *
         *  ①这种情况触发的是 ConfirmCallback 回调函数。
         *
         * */
        int a = 100;
        while (a > 0) {
            sendMessageService.sendDirectMessage("MyfanoutExchange-null","MyDirectRouting","MyfanoutExchange, I am amq sender num :" + a--);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test6(){
        /*
         *  消息确认触发回调函数
         *把消息推送到名为‘lonelyDirectExchange’的交换机上（这个交换机是没有任何队列配置的）
         *
         * 消息是推送成功到服务器了的，所以ConfirmCallback对消息确认情况是true；
         *RetrunCallback回调函数的打印参数里面可以看到，消息是推送到了交换机成功了，但是在路由分发给队列的时候，找不到队列，所以报了错误 NO_ROUTE 。
         *
         * ②这种情况触发的是 ConfirmCallback和RetrunCallback两个回调函数。
         *
         * */
        int a = 100;
        while (a > 0) {
            sendMessageService.sendDirectMessage("lonelyDirectExchange","MyDirectRouting","MyfanoutExchange, I am amq sender num :" + a--);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
