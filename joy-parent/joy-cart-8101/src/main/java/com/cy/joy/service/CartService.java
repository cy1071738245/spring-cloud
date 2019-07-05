package com.cy.joy.service;

import com.cy.joy.vo.CartVo;

import java.util.List;

public interface CartService {

    List<CartVo> MergerOfCartsService(List<CartVo> carts, Integer userId);

    List<CartVo> addCountService(Integer cartId, String userId);

    List<CartVo> subCountService(Integer cartId, String userId);

    List<CartVo> addCartService(CartVo cartVo);

    void clearCartService(String userId);

}
