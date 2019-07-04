package com.cy.joy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class JoyGateway9527Application {

    public static void main(String[] args) {
        SpringApplication.run(JoyGateway9527Application.class, args);
    }

}
