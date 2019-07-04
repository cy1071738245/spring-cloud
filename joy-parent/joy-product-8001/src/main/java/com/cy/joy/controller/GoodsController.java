package com.cy.joy.controller;

import com.cy.joy.enums.ResultState;
import com.cy.joy.pojo.Goods;
import com.cy.joy.service.GoodsService;
import com.cy.joy.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 查询商品列表
     * @return
     */
    @GetMapping("/list")
    public ResultVo<List<List<Goods>>> list(){
        ResultVo<List<List<Goods>>> result = new ResultVo<>();
        List<List<Goods>> list = goodsService.listService();
        result.setCode(ResultState.SUCCESS.getCode())
                .setMsg(ResultState.SUCCESS.getMsg())
                .setResult(list);
        return result;
    }

    @GetMapping("/get")
    public Goods get(@RequestParam("goodId")Integer goodId){
        return goodsService.getService(goodId);
    }

    @GetMapping("/discovery")
    public Object discovery(){
        List<String> list = discoveryClient.getServices();
        System.out.println("*****" + list);
        List<ServiceInstance> srvList = discoveryClient.getInstances("JOY-PRODUCT");
        for (ServiceInstance element : srvList) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
        }
        return this.discoveryClient;
    }

}
