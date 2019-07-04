package com.cy.joy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Users implements Serializable {

    private Integer userId;
    private String userName;
    private String userPass;
    private String userPhone;
    private String userEmail;
    private Integer userState;
    private String userImg;
    private String openId;

}
