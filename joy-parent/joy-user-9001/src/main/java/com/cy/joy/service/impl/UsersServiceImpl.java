package com.cy.joy.service.impl;

import com.alibaba.fastjson.JSON;
import com.cy.joy.mapper.UsersMapper;
import com.cy.joy.pojo.Users;
import com.cy.joy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private JedisPool jedisPool;

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

    @Override
    public Users getUserInRedis(String code) {
        Jedis jedis = jedisPool.getResource();
        String userJson = jedis.get(code);
        if(userJson != null){
            Users user = JSON.parseObject(userJson, Users.class);
            return user;
        }
        return null;
    }

    @Override
    public void setUserInRedis(String code, Users user) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(code, JSON.toJSONString(user));
    }

}
