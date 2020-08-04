package com.jimmy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiongyang
 * @date 2020/3/17 15:56
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadTest {
    @Autowired
    private ThreadPoolAnotationDemo01 demo01;

    @Test
    public  void test01() {
        demo01.test("功夫");
        demo01.test("英雄");
    }
}
