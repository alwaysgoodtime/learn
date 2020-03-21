package com.atguigu.scw;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.atguigu.scw.order.mapper")
@EnableFeignClients
@EnableEurekaClient
@EnableTransactionManagement
@EnableCircuitBreaker
@SpringBootApplication
public class ScwOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScwOrderApplication.class, args);
    }

}
