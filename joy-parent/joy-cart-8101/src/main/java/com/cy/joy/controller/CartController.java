package com.cy.joy.controller;

import com.cy.joy.service.CartService;
import com.cy.joy.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 购物车数据合并
     * @param carts
     * @param userId
     */
    @PostMapping("/MergerOfCarts")
    public List<CartVo> MergerOfCarts(@RequestBody(required = false) List<CartVo> carts, @RequestParam("userId") Integer userId){
        List<CartVo> result = cartService.MergerOfCartsService(carts, userId);
        return result;
    }

    /**
     * 购物车商品数量增加
     * @param cartId
     * @return
     */
    @GetMapping("/addCount")
    public List<CartVo> addCount(@RequestParam("cartId") Integer cartId){
        //需传参用户个人id
        System.out.println(cartId);
        return null;
    }

}
