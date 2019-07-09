package com.cy.joy.service;

import com.cy.joy.vo.OrderVo;
import com.cy.joy.vo.PayResultVo;

import java.util.List;

public interface OrderService {

    OrderVo getService(Integer userId);

    String setService(OrderVo orderVo);

    PayResultVo payFinishedService(PayResultVo payResultVo);

    OrderVo getDetailService(String orderId);

}
