package com.jimmy;

import com.jimmy.client.NettyClinet;
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
public class NettyClientApplication implements CommandLineRunner {

    @Autowired
    NettyClinet nettyClinet;



    public static void main(String[] args) {
        SpringApplication.run(NettyClientApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        nettyClinet.connect(8091,"127.0.0.1");
    }


}
