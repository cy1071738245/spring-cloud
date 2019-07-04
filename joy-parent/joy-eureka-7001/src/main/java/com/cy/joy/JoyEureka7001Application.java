package com.cy.joy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class JoyEureka7001Application {

    public static void main(String[] args) {
        SpringApplication.run(JoyEureka7001Application.class, args);
    }

}
