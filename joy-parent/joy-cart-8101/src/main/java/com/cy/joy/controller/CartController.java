package com.cy.joy.controller;

import com.cy.joy.config.JwtProperties;
import com.cy.joy.enums.ResultState;
import com.cy.joy.pojo.CartsParams;
import com.cy.joy.pojo.UserInfo;
import com.cy.joy.service.CartService;
import com.cy.joy.util.JwtUtils;
import com.cy.joy.vo.CartVo;
import com.cy.joy.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private CartService cartService;

    /**
     * 购物车数据合并
     * @param cartsParams
     */
    @PostMapping("/mergerOfCarts")
    public ResultVo<List<CartVo>> MergerOfCarts(@RequestBody(required = false) CartsParams cartsParams, @RequestHeader(value = "Authorization", required = false) String token){
        ResultVo<List<CartVo>> resultVo = new ResultVo<>();
        //options请求问题未解决
        token = cartsParams.getToken();
        UserInfo userInfo = null;
        try {
            userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            e.printStackTrace();
            return resultVo.setCode(ResultState.ERROR.getCode())
                    .setMsg(ResultState.ERROR.getMsg());
        }
        List<CartVo> result = cartService.MergerOfCartsService(cartsParams.getCarts(), userInfo.getId().intValue());
        resultVo.setCode(ResultState.SUCCESS.getCode());
        resultVo.setMsg(ResultState.SUCCESS.getMsg());
        resultVo.setResult(result);
        return resultVo;
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

    /**
     * 获取购物车信息
     * @param userId
     * @return
     */
    @GetMapping("/get")
    public List<CartVo> get(@RequestParam("userId") Integer userId){
        return cartService.getService(userId);
    }

}
