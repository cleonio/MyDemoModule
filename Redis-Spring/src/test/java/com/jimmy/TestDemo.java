package com.jimmy;

import com.jimmy.core.RedisService;
import com.jimmy.domain.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author xiongyang
 * @date 2020/7/30 16:52
 * @Description:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestDemo {

    @Autowired
    RedisService redisService;
    @Test
    public void test01(){
        redisService.set("test01","hello");
        String test01 = redisService.get("test01");
        System.out.println(test01);

        Message message = new Message();
        message.setId(1L);
        message.setMsg("hahaha");
        redisService.setObject("test0101",message);
        System.out.println(redisService.getObject("test0101").toString());
    }
}
