package com.cy.joy.service.impl;

import com.cy.joy.mapper.GoodsMapper;
import com.cy.joy.mapper.GoodsTypeMapper;
import com.cy.joy.pojo.Goods;
import com.cy.joy.pojo.GoodsType;
import com.cy.joy.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public List<List<Goods>> listService() {
        List<List<Goods>> list = new ArrayList<>();
        List<GoodsType> typeList = goodsTypeMapper.list();
        for (GoodsType goodsType : typeList) {
            List<Goods> goodsList = goodsMapper.listByType(goodsType.getTypeId());
            list.add(goodsList);
        }
        return list;
    }

    @Override
    public Goods getService(Integer goodId) {
        return goodsMapper.get(goodId);
    }

}
