package com.cy.joy.service.impl;

import com.cy.joy.pojo.Goods;
import com.cy.joy.service.GoodsClientService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoodsClientServiceFallBackFactory implements FallbackFactory<GoodsClientService> {

    @Override
    public GoodsClientService create(Throwable throwable) {
        return new GoodsClientService() {
            @Override
            public List<Goods> listService() {
                return null;
            }

            @Override
            public Goods getService(Integer goodId) {
                return new Goods().setGoodsName("ID:"+goodId+"没有对应得商品信息,商品服务已关闭");
            }
        };
    }
}
