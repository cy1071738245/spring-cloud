package com.cy.joy.service.impl;

import com.cy.joy.mapper.UsersMapper;
import com.cy.joy.pojo.Users;
import com.cy.joy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users getOpenIdService(String openId) {
        return usersMapper.getOpenId(openId);
    }

    @Override
    public void addUserService(Users user) {
        usersMapper.addUser(user);
    }

    @Override
    public void recoverUserService(String openId) {
        usersMapper.recoverUser(openId);
    }

}
