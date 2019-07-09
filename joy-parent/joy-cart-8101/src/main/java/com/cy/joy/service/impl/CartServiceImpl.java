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
    private GoodsClientService goodsClientService;
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private CartMapper cartMapper;


    /**
     * 购物车数据合并
     * @param carts
     * @param userId
     */
    @Override
    public List<CartVo> MergerOfCartsService(List<CartVo> carts, Integer userId) {
        Jedis jedis = jedisPool.getResource();
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
        return this.returnCartInfo(result, carts);
    }

    /**
     * 购物车商品数量增加
     * @param cartId
     * @param userId
     * @return
     */
    @Override
    public List<CartVo> addCountService(Integer cartId, String userId) {
        Jedis jedis = jedisPool.getResource();
        Map<String,String> map = jedis.hgetAll(userId);
        String jsonStr = map.get(String.valueOf(cartId));
        Cart cart = JSON.parseObject(jsonStr, Cart.class);
        Integer cartNum = cart.getCartNum()+1;
        cart.setCartPrice(cart.getCartPrice()/cart.getCartNum()*cartNum)
                .setCartNum(cartNum);
        map.put(String.valueOf(cartId), JSON.toJSONString(cart));
        jedis.hmset(userId,map);

        //返回购物车信息
        return this.returnCartInfo(map, new ArrayList<>());
    }

    /**
     * 购物车商品数量减少
     * @param cartId
     * @param userId
     * @return
     */
    @Override
    public List<CartVo> subCountService(Integer cartId, String userId) {
        Jedis jedis = jedisPool.getResource();
        Map<String,String> map = jedis.hgetAll(userId);
        String jsonStr = map.get(String.valueOf(cartId));
        Cart cart = JSON.parseObject(jsonStr, Cart.class);
        Integer cartNum = cart.getCartNum()-1;
        if(cartNum == 0){
            jedis.hdel(userId, String.valueOf(cartId));
        }else {
            cart.setCartPrice(cart.getCartPrice()/cart.getCartNum()*cartNum)
                    .setCartNum(cartNum);
            map.put(String.valueOf(cartId), JSON.toJSONString(cart));
            jedis.hmset(userId,map);
        }

        //返回购物车信息
        return this.returnCartInfo(map, new ArrayList<>());
    }

    /**
     * 添加购物车
     * @param cartVo
     * @return
     */
    @Override
    public List<CartVo> addCartService(CartVo cartVo) {
        Jedis jedis = jedisPool.getResource();
        Map<String,String> map = jedis.hgetAll(String.valueOf(cartVo.getCurrentUserId()));
        Cart cart;
        if(map != null && map.size() > 0){
            if(map.get(String.valueOf(cartVo.getProId())) != null){
                cart = JSON.parseObject(map.get(String.valueOf(cartVo.getProId())), Cart.class);
                Integer cartNum = cart.getCartNum() + cartVo.getProNumber();
                cart.setCartPrice(cart.getCartPrice()/cart.getCartNum()*cartNum)
                        .setCartNum(cartNum);
            }else {
                cart = new Cart().setCartGoods(cartVo.getProId())
                        .setCartNum(cartVo.getProNumber())
                        .setCartPrice(cartVo.getProTotalPrice())
                        .setCartUser(cartVo.getCurrentUserId());
            }
        }else {
            map = new HashMap<>();
            cart = new Cart().setCartGoods(cartVo.getProId())
                    .setCartNum(cartVo.getProNumber())
                    .setCartPrice(cartVo.getProTotalPrice())
                    .setCartUser(cartVo.getCurrentUserId());
        }
        map.put(String.valueOf(cart.getCartGoods()), JSON.toJSONString(cart));
        jedis.hmset(String.valueOf(cartVo.getCurrentUserId()), map);

        //返回购物车信息
        return this.returnCartInfo(map, new ArrayList<>());
    }

    /**
     * 清空所有
     * @param userId
     */
    @Override
    public void clearCartService(String userId) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(userId);
    }

    /**
     * 获取购物车信息
     * @param userId
     * @return
     */
    @Override
    public List<CartVo> getService(Integer userId) {
        Jedis jedis = jedisPool.getResource();
        Map<String,String> map = jedis.hgetAll(String.valueOf(userId));
        return this.returnCartInfo(map, new ArrayList<>());
    }

    /**
     * 返回购物车信息
     * @param map
     * @param list
     * @return
     */
    private List<CartVo> returnCartInfo(Map<String,String> map, List<CartVo> list){
        Collection<String> collection = map.values();
        for (String value : collection) {
            Cart resultCart = JSON.parseObject(value,Cart.class);
            CartVo cartVo = new CartVo()
                    .setProId(resultCart.getCartGoods())
                    .setProName(goodsClientService.getService(resultCart.getCartGoods()).getGoodsName())
                    .setProNumber(resultCart.getCartNum())
                    .setProPrice(resultCart.getCartPrice()/resultCart.getCartNum())
                    .setProTotalPrice(resultCart.getCartPrice());
            list.add(cartVo);
        }
        return list;
    }

}
