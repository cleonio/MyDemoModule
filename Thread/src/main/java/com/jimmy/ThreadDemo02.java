package com.jimmy;

/**
 * @author xiongyang
 * @date 2020/3/17 14:45
 * @Description: 实现Runnable接口
 *
 * 1写一个子类实现Runnable接口
 * 2复写run方法
 * 3在测试类中创建Runnable子类对象
 * 4创建Thread对象，Runnable子类对象当做参数传递
 * 5调用Thread的start()方法开启线程
 */
public class ThreadDemo02 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            String name = Thread.currentThread().getName();
            System.out.println(i+"--"+name);
        }

    }
}

class Test02{

    public static void main(String[] args) {
        ThreadDemo02 demo01 = new ThreadDemo02();
        Thread thread01 = new Thread(demo01);
        thread01.setName("功夫");
        thread01.start();

        Thread thread02 = new Thread(demo01);
        thread02.setName("英雄");
        thread02.start();

    }

}
