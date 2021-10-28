package com.jimmy;

import com.jimmy.server.MyNettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiongyang
 * @date 2020/8/6 15:58
 * @Description:
 */
@SpringBootApplication
public class NettyServerApplication implements CommandLineRunner {

    @Autowired
    MyNettyServer myNettyServer;



    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        myNettyServer.start("127.0.0.1",8091);
    }
}
