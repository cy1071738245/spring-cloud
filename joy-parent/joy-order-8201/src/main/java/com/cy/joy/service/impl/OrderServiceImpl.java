package com.cy.joy.service.impl;

import com.cy.joy.mapper.OrderMapper;
import com.cy.joy.pojo.Goods;
import com.cy.joy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Goods> listService() {
        return orderMapper.list();
    }

    @Override
    public Goods getService(Integer goodId) {
        return orderMapper.get(goodId);
    }

}
