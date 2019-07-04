package com.cy.joy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

@Configuration
public class RedisConfig {

    @Value("127.0.0.1")
    private String host;

    @Value("6379")
    private int port;

    @Bean
    public JedisPool jedisPool(){
        return new JedisPool(host, port);
    }

}
