package com.jimmy;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author xiongyang
 * @date 2020/3/17 15:48
 * @Description:  springboot测试线程池
 */
@Component
public class ThreadPoolAnotationDemo01 {

    @Async("asyncServiceExecutor")
    public void test(String name){
        for (int i = 0; i <100 ; i++) {
            System.out.println(i+name);
        }

    }
}
