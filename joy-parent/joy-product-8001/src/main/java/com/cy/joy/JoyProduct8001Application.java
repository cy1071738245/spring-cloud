package com.cy.joy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan(value = "com.cy.joy.mapper")
@EnableDiscoveryClient
public class JoyProduct8001Application {

    public static void main(String[] args) {
        SpringApplication.run(JoyProduct8001Application.class, args);
    }

}
