package com.jimmy;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author xiongyang
 * @date 2020/3/30 20:31
 * @Description:
 */

@Component
public class TaskTest {

    @Async("asyncServiceExecutor")
    public  void  task(Integer id){
        int index = 0;
        long l = System.currentTimeMillis();
        while (true){
            //查询订单状态

            if (index ==/*360*/10){
                long l2 = System.currentTimeMillis();
                System.out.println("耗时"+(l2-l)/1000);
                break;
            }
            System.out.println(Thread.currentThread().getName()+"   id:"+id+"index:"+index+"查询订单状态");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            index++;
        }
    }
}
