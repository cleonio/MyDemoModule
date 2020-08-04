package com.jimmy;

/**
 * @author xiongyang
 * @date 2020/3/17 14:45
 * @Description: 匿名内部类方式
 *
 */
public class ThreadDemo03{

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(i+"功夫");
                }
            }
        });

        thread.start();
        Thread thread02 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(i+"英雄");
                }
            }
        });
        thread02.start();
    }
}


