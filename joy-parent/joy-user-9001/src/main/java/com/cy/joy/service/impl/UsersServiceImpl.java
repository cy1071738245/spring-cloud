package com.cy.joy.service.impl;

import com.alibaba.fastjson.JSON;
import com.cy.joy.mapper.UsersMapper;
import com.cy.joy.pojo.Users;
import com.cy.joy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private UsersMapper usersMapper;

    @Override
    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    public Users getOpenIdService(String openId) {
        return usersMapper.getOpenId(openId);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
    public void addUserService(Users user) {
        usersMapper.addUser(user);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
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
        jedis.setex(code, 1800, JSON.toJSONString(user));
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    public Users getService(Integer userId) {
        return usersMapper.get(userId);
    }
}
