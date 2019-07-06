package com.cy.joy.service.impl;

import com.cy.joy.mapper.OrderMapper;
import com.cy.joy.service.CartClientService;
import com.cy.joy.service.GoodsClientService;
import com.cy.joy.service.OrderService;
import com.cy.joy.vo.CartVo;
import com.cy.joy.vo.OrderDetailVo;
import com.cy.joy.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartClientService cartClientService;

    @Autowired
    private GoodsClientService goodsClientService;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 获取订单信息
     * @param userId
     * @return
     */
    @Override
    public OrderVo getService(Integer userId) {
        List<CartVo> cartVoList = cartClientService.get(userId);
        List<OrderDetailVo> orderDetailVoList = new ArrayList<>();
        Double orderTotalPrice = 0d;
        for (CartVo cartVo : cartVoList) {
            String imgUrl = goodsClientService.getService(cartVo.getProId()).getGoodsImg();
            OrderDetailVo orderDetailVo = new OrderDetailVo()
                    .setOrderDetailImg(imgUrl)
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

}
