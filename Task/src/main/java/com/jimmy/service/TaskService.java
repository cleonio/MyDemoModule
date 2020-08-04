package com.jimmy.service;

import com.jimmy.TaskTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author xiongyang
 * @date 2020/3/30 19:15
 * @Description:
 */

@Service
public class TaskService {

    @Autowired
    TaskTest taskTest;


    public void testTask(){
        for (int i = 0; i < 10; i++) {

            /*int finalI = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    taskTest.task(finalI);
                }
            });*/

            taskTest.task(i);
        }

    }



}
