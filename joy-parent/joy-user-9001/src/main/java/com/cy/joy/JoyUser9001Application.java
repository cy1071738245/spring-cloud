package com.cy.joy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan(value = "com.cy.joy.mapper")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.cy.joy"})
public class JoyUser9001Application {

    public static void main(String[] args) {
        SpringApplication.run(JoyUser9001Application.class, args);
    }

}
