package com.cy.joy.service;

import com.cy.joy.pojo.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsService {

    List<List<Goods>> listService();

    Goods getService(Integer goodId);

}
