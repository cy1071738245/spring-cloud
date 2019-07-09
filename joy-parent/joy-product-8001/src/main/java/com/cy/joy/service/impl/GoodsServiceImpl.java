package com.cy.joy.service.impl;

import com.alibaba.fastjson.JSON;
import com.cy.joy.mapper.GoodsMapper;
import com.cy.joy.mapper.GoodsTypeMapper;
import com.cy.joy.pojo.Goods;
import com.cy.joy.pojo.GoodsType;
import com.cy.joy.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<List<Goods>> listService() {
        Jedis jedis = jedisPool.getResource();
        String jsonStr = jedis.get("all");
        if(jsonStr == null || jsonStr.equals("")){
            List<List<Goods>> list = new ArrayList<>();
            List<GoodsType> typeList = goodsTypeMapper.list();
            for (GoodsType goodsType : typeList) {
                List<Goods> goodsList = goodsMapper.listByType(goodsType.getTypeId());
                list.add(goodsList);
            }
            jedis.set("all", JSON.toJSONString(list));
            return list;
        }else{
            return JSON.parseObject(jsonStr, List.class);
        }
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    public Goods getService(Integer goodId) {
        return goodsMapper.get(goodId);
    }

}
