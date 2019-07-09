package com.cy.joy.service.impl;

import com.alibaba.fastjson.JSON;
import com.cy.joy.mapper.OrderMapper;
import com.cy.joy.service.CartClientService;
import com.cy.joy.service.GoodsClientService;
import com.cy.joy.service.OrderService;
import com.cy.joy.vo.CartVo;
import com.cy.joy.vo.OrderDetailVo;
import com.cy.joy.vo.OrderVo;
import com.cy.joy.vo.PayResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartClientService cartClientService;
    @Autowired
    private GoodsClientService goodsClientService;
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 获取订单信息
     * @param userId
     * @return
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    public OrderVo getService(Integer userId) {
        List<CartVo> cartVoList = cartClientService.get(userId);
        List<OrderDetailVo> orderDetailVoList = new ArrayList<>();
        Double orderTotalPrice = 0d;
        for (CartVo cartVo : cartVoList) {
            String imgUrl = goodsClientService.getService(cartVo.getProId()).getGoodsImg();
            OrderDetailVo orderDetailVo = new OrderDetailVo()
                    .setOrderDetailImg(imgUrl)
                    .setOrderDetailGoodId(cartVo.getProId())
                    .setOrderDetailName(cartVo.getProName())
                    .setOrderDetailNum(cartVo.getProNumber())
                    .setOrderDetailPrice(cartVo.getProTotalPrice());
            orderDetailVoList.add(orderDetailVo);
            orderTotalPrice += cartVo.getProTotalPrice();
        }
        OrderVo orderVo = new OrderVo()
                .setTypeNum(orderDetailVoList.size())
                .setOrderTotalPrice(orderTotalPrice)
                .setOrderDetailList(orderDetailVoList);
        return orderVo;
    }

    /**
     * 订单信息保存
     * @param orderVo
     * @return
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
    public String setService(OrderVo orderVo) {
        Jedis jedis = jedisPool.getResource();
        String orderId = UUID.randomUUID().toString().replaceAll("-", "");
        for (OrderDetailVo orderDetailVo : orderVo.getOrderDetailList()) {
            orderMapper.setOrderDetail(orderId, orderDetailVo);
        }
        orderMapper.setOrder(orderId, orderVo);
        //将订单信息暂存redis
        jedis.set(orderId, JSON.toJSONString(orderVo));
        jedis.setex(orderId, 900, JSON.toJSONString(orderVo));
        jedis.del(orderVo.getOrderUser().getUserId().toString());
        return orderId;
    }

    /**
     * 修改订单状态
     * @param payResultVo
     * @return
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
    public PayResultVo payFinishedService(PayResultVo payResultVo) {
        Jedis jedis = jedisPool.getResource();
        payResultVo.setCreateTime(new Date());
        orderMapper.payFinished(payResultVo.getOrderId());
        jedis.del(payResultVo.getOrderId());
        return payResultVo;
    }

    /**
     * 获取支付后详情信息
     * @param orderId
     * @return
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    public OrderVo getDetailService(String orderId) {
        Double orderPrice = orderMapper.getOrderPrice(orderId);
        List<OrderDetailVo> orderDetails = orderMapper.getDetail(orderId);
        OrderVo orderVo = new OrderVo().setOrderTotalPrice(orderPrice)
                .setOrderDetailList(orderDetails)
                .setTypeNum(orderDetails.size());
        return orderVo;
    }

}
