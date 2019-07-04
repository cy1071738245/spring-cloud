package com.cy.joy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan(value = "com.cy.joy.mapper")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.cy.joy"})
public class JoyCart8101Application {

    public static void main(String[] args) {
        SpringApplication.run(JoyCart8101Application.class, args);
    }

}
