package com.jimmy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xiongyang
 * @date 2020/3/17 15:17
 * @Description:
 */
public class ThreadUtilDemo {

    public static void main(String[] args) {
        try {
            System.out.println(Thread.currentThread().getId());
            ThreadUtil.execute(()->{
                for (int i = 0; i <100 ; i++) {
                    System.out.println(i+"英雄");
                    if (i==50){
                        try {
                            System.out.println(Thread.currentThread().getId());
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            ThreadUtil.execute(()->{
                System.out.println(Thread.currentThread().getId());
                for (int i = 0; i <100 ; i++) {
                    System.out.println(i+"功夫");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
