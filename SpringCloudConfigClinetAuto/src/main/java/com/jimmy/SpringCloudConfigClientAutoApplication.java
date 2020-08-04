package com.jimmy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiongyang
 * @date 2020/3/31 9:49
 * @Description: 创建配置中心服务端
 */

@SpringBootApplication
public class SpringCloudConfigClientAutoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigClientAutoApplication.class,args);
    }

}

