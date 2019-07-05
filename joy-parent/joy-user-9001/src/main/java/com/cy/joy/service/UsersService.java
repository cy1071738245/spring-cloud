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

    /**
     * 从redis取出用户信息
     * @param code
     * @return
     */
    Users getUserInRedis(String code);

    /**
     * 授权后用户信息保存
     * @param code
     * @param user
     */
    void setUserInRedis(String code, Users user);

}
