package com.jimmy.constant;

/**
 * @author xiongyang
 * @date 2020/3/31 10:10
 * @Description:
 */
public class MQConstant {

    private MQConstant(){}

    //exchange name
    public static final String DEFAULT_EXCHANGE = "ZyChange";

    //TTL QUEUE
    public static final String DEFAULT_DEAD_LETTER_QUEUE_NAME = "zy.dead.letter.queue";

    //DLX repeat QUEUE 死信转发队列
    public static final String DEFAULT_REPEAT_TRADE_QUEUE_NAME = "zy.repeat.trade.queue";

    //Hello 测试消息队列名称
    public static final String HELLO_QUEUE_NAME = "HELLO";
}
