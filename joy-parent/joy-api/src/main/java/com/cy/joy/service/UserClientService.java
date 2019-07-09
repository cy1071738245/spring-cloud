package com.cy.joy.service;

import com.cy.joy.pojo.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "JOY-USER")
public interface UserClientService {

    @PostMapping("/user/get")
    Users get(@RequestParam("userId") Integer userId);

}
