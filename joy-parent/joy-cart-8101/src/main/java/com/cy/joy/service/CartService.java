package com.cy.joy.service;

import com.cy.joy.vo.CartVo;

import java.util.List;

public interface CartService {

    List<CartVo> MergerOfCartsService(List<CartVo> carts, Integer userId);

    List<CartVo> addCount(Integer cartId);

}
