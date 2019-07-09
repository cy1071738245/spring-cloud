package com.cy.joy.service.impl;

import com.cy.joy.mapper.GoodsTypeMapper;
import com.cy.joy.pojo.GoodsType;
import com.cy.joy.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<GoodsType> listService() {
        return goodsTypeMapper.list();
    }

}
