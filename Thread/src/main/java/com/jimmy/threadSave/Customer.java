package com.jimmy.threadSave;

/**
 * @author xiongyang
 * @date 2020/3/17 17:30
 * @Description:
 */
public class Customer implements Runnable {

    private Cake cake;

    public Customer(Cake cake) {
        this.cake = cake;
    }


    @Override
    public void run() {
        while(true){
            //锁对象：包子对象，被两个线程共享，而且是唯一的
            synchronized (cake){
                //判断包子的状态
                if(!cake.isFlag()){ //如没有包子，才等待
                    try {
                        cake.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("消费者开始吃了一个包子..."+cake.getWeidao());
                cake.setFlag(false);//把状态该为false
                cake.notify(); //唤醒正在等待的线程 （生产者）
            }
        }
    }
}
