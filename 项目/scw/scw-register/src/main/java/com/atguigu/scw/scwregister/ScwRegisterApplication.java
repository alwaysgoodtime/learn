package com.atguigu.scw.scwregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//开启Eureka服务管理
@EnableEurekaServer
//启用自动配置等
@SpringBootApplication
public class ScwRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScwRegisterApplication.class, args);
    }

}
