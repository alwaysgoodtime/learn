package com.atguigu.scw.scwregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ScwRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScwRegisterApplication.class, args);
    }

}
