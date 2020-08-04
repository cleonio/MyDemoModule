package com.jimmy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author xiongyang
 * @date 2020/3/31 9:49
 * @Description: 创建配置中心服务端
 */

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudConfigEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigEurekaClientApplication.class,args);
    }

}

