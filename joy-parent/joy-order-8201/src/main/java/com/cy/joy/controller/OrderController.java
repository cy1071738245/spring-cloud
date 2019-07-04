package com.cy.joy.controller;

import com.cy.joy.pojo.Goods;
import com.cy.joy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public List<Goods> list(){
        return orderService.listService();
    }

    @GetMapping("/get/{goodId}")
    public Goods get(@PathVariable("goodId") Integer goodId){
        return orderService.getService(goodId);
    }

}
