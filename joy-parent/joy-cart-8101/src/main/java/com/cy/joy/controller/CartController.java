package com.cy.joy.controller;

import com.cy.joy.enums.ResultState;
import com.cy.joy.service.CartService;
import com.cy.joy.vo.CartVo;
import com.cy.joy.vo.ResultVo;
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
     * @param userId
     * @return
     */
    @GetMapping("/addCount")
    public ResultVo<List<CartVo>> addCount(@RequestParam("cartId") Integer cartId, @RequestParam("userId") String userId){
        ResultVo<List<CartVo>> resultVo = new ResultVo<>();
        List<CartVo> result = cartService.addCountService(cartId, userId);
        resultVo.setCode(ResultState.SUCCESS.getCode());
        resultVo.setMsg(ResultState.SUCCESS.getMsg());
        resultVo.setResult(result);
        return resultVo;
    }

    /**
     * 购物车商品数量减少
     * @param cartId
     * @param userId
     * @return
     */
    @GetMapping("/subCount")
    public ResultVo<List<CartVo>> subCount(@RequestParam("cartId") Integer cartId, @RequestParam("userId") String userId){
        ResultVo<List<CartVo>> resultVo = new ResultVo<>();
        List<CartVo> result = cartService.subCountService(cartId, userId);
        resultVo.setCode(ResultState.SUCCESS.getCode());
        resultVo.setMsg(ResultState.SUCCESS.getMsg());
        resultVo.setResult(result);
        return resultVo;
    }

    /**
     * 添加购物车
     * @param cart
     * @return
     */
    @PostMapping("/addCart")
    public ResultVo<List<CartVo>> addCart(@RequestBody CartVo cart){
        ResultVo<List<CartVo>> resultVo = new ResultVo<>();
        List<CartVo> result = cartService.addCartService(cart);
        resultVo.setCode(ResultState.SUCCESS.getCode());
        resultVo.setMsg(ResultState.SUCCESS.getMsg());
        resultVo.setResult(result);
        return resultVo;
    }

    /**
     * 清空所有
     * @param userId
     */
    @GetMapping("/clearCart")
    public void clearCart(@RequestParam("userId") String userId){
        cartService.clearCartService(userId);
    }

}
