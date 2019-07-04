package com.cy.joy.controller;

import com.cy.joy.pojo.GoodsType;
import com.cy.joy.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    @GetMapping("list")
    public List<GoodsType> list(){
        return goodsTypeService.listService();
    }

}
