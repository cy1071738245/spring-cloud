package com.cy.joy.service.impl;

import com.alibaba.fastjson.JSON;
import com.cy.joy.mapper.CartMapper;
import com.cy.joy.pojo.Cart;
import com.cy.joy.service.CartService;
import com.cy.joy.service.GoodsClientService;
import com.cy.joy.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private GoodsClientService goodsClientService;

    /**
     * 购物车数据合并
     * @param carts
     * @param userId
     */
    @Override
    public List<CartVo> MergerOfCartsService(List<CartVo> carts, Integer userId) {
        Jedis jedis=jedisPool.getResource();
        //开始数据合并
        Map<String,String> result = jedis.hgetAll(String.valueOf(userId));
        //未登录购物车判空
        if(carts.get(0).getProId() == null && result == null){
            return null;
        }
        if(carts.get(0).getProId() != null){
            //如果已经有购物车信息
            if(result != null && result.size() > 0){
                for (CartVo cartVo : carts) {
                    Cart cart = new Cart().setCartGoods(cartVo.getProId())
                            .setCartNum(cartVo.getProNumber())
                            .setCartPrice(cartVo.getProTotalPrice())
                            .setCartUser(userId);
                    result.put(String.valueOf(cart.getCartGoods()), JSON.toJSONString(cart));
                }
            }else {
                result = new HashMap<>();
                for (CartVo cartVo : carts) {
                    Cart cart = new Cart().setCartGoods(cartVo.getProId())
                            .setCartNum(cartVo.getProNumber())
                            .setCartPrice(cartVo.getProTotalPrice())
                            .setCartUser(userId);
                    result.put(String.valueOf(cart.getCartGoods()), JSON.toJSONString(cart));
                }
            }
            jedis.hmset(String.valueOf(userId),result);
        }
        //返回购物车信息
        carts.clear();
        Collection<String> collection = result.values();
        List<String> list = new ArrayList<>(collection);
        for (String jsonStr : list) {
            Cart cart = JSON.parseObject(jsonStr,Cart.class);
            CartVo cartVo = new CartVo()
                    .setProId(cart.getCartGoods())
                    .setProName(goodsClientService.getService(cart.getCartGoods()).getGoodsName())
                    .setProNumber(cart.getCartNum())
                    .setProPrice(cart.getCartPrice()/cart.getCartNum())
                    .setProTotalPrice(cart.getCartPrice());
            carts.add(cartVo);
        }
        return carts;
    }

    /**
     * 购物车商品数量增加
     * @param cartId
     * @return
     */
    @Override
    public List<CartVo> addCount(Integer cartId) {
        Jedis jedis=jedisPool.getResource();
        
        return null;
    }

}
