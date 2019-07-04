package com.cy.joy.service;

import com.cy.joy.pojo.Goods;

import java.util.List;

public interface OrderService {

    List<Goods> listService();

    Goods getService(Integer goodId);

}
