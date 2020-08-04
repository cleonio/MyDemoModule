package com.jimmy;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiongyang
 * @date 2020/3/30 20:49
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {



    @org.junit.Test
    public void Test() throws InterruptedException {


        //for (int i = 0; i < 10; i++) {

            int finalI = 1;
            Thread thread = new Thread(new Runnable() {


                @Override
                public void run() {

                    TaskTest test = new TaskTest();
                    test.task(finalI);
                }
            });

            thread.start();
        //}


        Thread.sleep(500000);
    }
}
