package com.cy.joy.mapper;

import com.cy.joy.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMapper {

    /**
     * 查询授权用户是否存在
     * @param openId
     * @return
     */
    Users getOpenId(@Param("openId") String openId);

    /**
     * 注册用户
     * @param user
     */
    void addUser(@Param("user") Users user);

    /**
     * 删除用户
     * @param openId
     */
    void deleteUser(@Param("openId") String openId);

    /**
     * 恢复用户
     * @param openId
     */
    void recoverUser(@Param("openId") String openId);

    /**
     * 根据id获取用户
     * @param userId
     * @return
     */
    Users get(@Param("userId") Integer userId);

}
