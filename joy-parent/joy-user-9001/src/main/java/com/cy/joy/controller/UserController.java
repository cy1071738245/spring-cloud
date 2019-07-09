package com.cy.joy.controller;

import com.cy.joy.pojo.Users;
import com.cy.joy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/get")
    public Users get(@RequestParam("userId") Integer userId){
        return usersService.getService(userId);
    }

}
