package com.cy.joy.controller;

import com.cy.joy.enums.ResultState;
import com.cy.joy.pojo.Users;
import com.cy.joy.service.OrderService;
import com.cy.joy.vo.OrderVo;
import com.cy.joy.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 获取订单信息
     * @param user
     * @return
     */
    @PostMapping("/get")
    public ResultVo<OrderVo> get(@RequestBody Users user){
        ResultVo<OrderVo> resultVo = new ResultVo<OrderVo>();
        OrderVo result = orderService.getService(user.getUserId());
        resultVo.setCode(ResultState.SUCCESS.getCode())
                .setMsg(ResultState.SUCCESS.getMsg())
                .setResult(result);
        return resultVo;
    }

}
