package com.jimmy;

/**
 * @author xiongyang
 * @date 2020/3/17 14:45
 * @Description: 继承Thread方式开启线程
 *
 * 1写一个子类继承Thread
 * 2复写run方法
 * 3在测试类中创建子类对象
 * 4调用 start()方法开启线程
 */
public class ThreadDemo01 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            String name = Thread.currentThread().getName();
            System.out.println(i+"--"+name);
        }

    }
}

class Test{

    public static void main(String[] args) {

        ThreadDemo01 demo01 = new ThreadDemo01();
        demo01.setName("功夫");
        demo01.start();

        ThreadDemo01 demo02 = new ThreadDemo01();
        demo02.setName("英雄");
        demo02.start();

    }



}
