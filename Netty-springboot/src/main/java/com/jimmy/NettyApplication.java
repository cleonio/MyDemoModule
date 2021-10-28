package com.jimmy;

import com.jimmy.netty.NettyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiongyang
 * @date 2020/8/6 15:58
 * @Description:
 */
@SpringBootApplication
public class NettyApplication implements CommandLineRunner {

    @Autowired
    NettyService nettyService;



    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        nettyService.start();
    }
}
