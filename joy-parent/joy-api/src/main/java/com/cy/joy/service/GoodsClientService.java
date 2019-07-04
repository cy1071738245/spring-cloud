package com.cy.joy.service;

import com.cy.joy.pojo.Goods;
import com.cy.joy.service.impl.GoodsClientServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "JOY-PRODUCT", fallbackFactory = GoodsClientServiceFallBackFactory.class)
public interface GoodsClientService {

    @GetMapping("/goods/list")
    List<Goods> listService();

    @GetMapping("/goods/get")
    Goods getService(@RequestParam("goodId") Integer goodId);

}
