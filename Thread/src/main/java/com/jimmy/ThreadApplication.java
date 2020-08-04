package com.jimmy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiongyang
 * @date 2020/3/17 16:06
 * @Description:
 */

@SpringBootApplication(scanBasePackages = {"com.jimmy"})
public class ThreadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadApplication.class,args);
    }
}
