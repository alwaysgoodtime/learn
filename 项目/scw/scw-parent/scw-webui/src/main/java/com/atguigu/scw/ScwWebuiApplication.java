package com.atguigu.scw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//使用熔断器功能
@EnableCircuitBreaker
//开启feign，远程rest调用
@EnableFeignClients
//让注册中心能够发现，扫描改服务
@EnableDiscoveryClient
@SpringBootApplication
public class ScwWebuiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScwWebuiApplication.class, args);
    }

}
