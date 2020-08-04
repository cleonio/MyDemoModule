package com.jimmy.threadSave;

import com.jimmy.ThreadUtil;

/**
 * @author xiongyang
 * @date 2020/3/17 17:10
 * @Description:  线程安全  线程通信
 */
public class ThreadSaveCommunication {

    public static void main(String[] args) {
        Cake cake = new Cake();
        try {
            ThreadUtil.execute(new Producer(cake));
            ThreadUtil.execute(new Customer(cake));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
