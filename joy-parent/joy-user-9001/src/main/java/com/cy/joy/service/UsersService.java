package com.cy.joy.service;

import com.cy.joy.pojo.Users;

public interface UsersService {

    /**
     * 查询授权用户是否存在
     * @param openId
     * @return
     */
    Users getOpenIdService(String openId);

    /**
     * 注册用户
     * @param user
     */
    void addUserService(Users user);

    /**
     * 恢复用户
     * @param openId
     */
    void recoverUserService(String openId);

}
