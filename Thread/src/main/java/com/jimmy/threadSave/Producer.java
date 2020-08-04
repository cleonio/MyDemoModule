package com.jimmy.threadSave;

/**
 * @author xiongyang
 * @date 2020/3/17 17:15
 * @Description:
 */
public class Producer implements Runnable {

    private Cake cake;

    public Producer(Cake cake) {
        this.cake = cake;
    }

    @Override
    public void run() {
        while(true){
            //锁对象：包子对象，被两个线程共享，而且是唯一的
            synchronized (cake){
                //判断包子的状态
                if(cake.isFlag()){ //如果有包子
                    try {
                        cake.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                cake.setWeidao("榴莲味");
                System.out.println("生产者开始生产了一个包子..."+cake.getWeidao());
                cake.setFlag(true);//把状态该为true
                cake.notify();//唤醒正在等待的线程 （消费者）
            }
        }
    }
}
