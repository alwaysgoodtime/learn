package com.atguigu.scw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//使用熔断器功能
@EnableCircuitBreaker
//开启feign，远程rest调用
@EnableFeignClients
@EnableTransactionManagement //开启事务
@EnableDiscoveryClient  //开启服务注册发现功能
@MapperScan("com.atguigu.scw.project.mapper")//扫描的mapper类所在的包
@SpringBootApplication
public class ScwProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(ScwProjectApplication.class, args);
    }

}
