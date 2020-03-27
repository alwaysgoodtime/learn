package com.goodtime.highconcurrencytask;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.goodtime.highconcurrencytask.mapper")
public class HighconcurrencytaskApplication {

    public static void main(String[] args) {

        SpringApplication.run(HighconcurrencytaskApplication.class, args);
    }

}
