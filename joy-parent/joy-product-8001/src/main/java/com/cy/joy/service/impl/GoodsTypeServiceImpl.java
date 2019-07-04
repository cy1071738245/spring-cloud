package com.cy.joy.service.impl;

import com.cy.joy.mapper.GoodsTypeMapper;
import com.cy.joy.pojo.GoodsType;
import com.cy.joy.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public List<GoodsType> listService() {
        return goodsTypeMapper.list();
    }

}
