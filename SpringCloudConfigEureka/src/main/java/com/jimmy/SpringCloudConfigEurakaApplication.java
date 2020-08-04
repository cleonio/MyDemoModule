package com.jimmy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author xiongyang
 * @date 2020/3/31 9:49
 * @Description: 创建配置中心服务端
 */

@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class SpringCloudConfigEurakaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigEurakaApplication.class,args);
    }
}
