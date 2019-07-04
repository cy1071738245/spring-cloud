package com.cy.joy.wx.pojo;

import com.cy.joy.vo.CartVo;
import lombok.Data;

import java.util.List;

@Data
public class LoginParams {

    private String code;
    private List<CartVo> carts;

}
