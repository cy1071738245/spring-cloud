package com.cy.joy.controller;

import com.cy.joy.config.JwtProperties;
import com.cy.joy.enums.ResultState;
import com.cy.joy.pojo.UserInfo;
import com.cy.joy.pojo.Users;
import com.cy.joy.service.OrderService;
import com.cy.joy.service.UserClientService;
import com.cy.joy.util.JwtUtils;
import com.cy.joy.vo.OrderVo;
import com.cy.joy.vo.PayResultVo;
import com.cy.joy.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserClientService userClientService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private OrderService orderService;

    /**
     * 获取订单信息
     * @param user
     * @return
     */
    @PostMapping("/get")
    public ResultVo<OrderVo> get(@RequestBody Users user) {
        ResultVo<OrderVo> resultVo = new ResultVo<>();
        OrderVo result = orderService.getService(user.getUserId());
        resultVo.setCode(ResultState.SUCCESS.getCode())
                .setMsg(ResultState.SUCCESS.getMsg())
                .setResult(result);
        return resultVo;
    }

    /**
     * 订单信息保存
     * @param orderVo
     * @return
     */
    @PostMapping("/set")
    public ResultVo<String> set(@RequestBody OrderVo orderVo){
        ResultVo<String> resultVo = new ResultVo();
        String token = orderVo.getToken();
        UserInfo userInfo = null;
        try {
            userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            e.printStackTrace();
            return resultVo.setCode(ResultState.ERROR.getCode())
                    .setMsg(ResultState.ERROR.getMsg());
        }
        Users user = userClientService.get(userInfo.getId().intValue());
        orderVo.setOrderUser(user);
        String orderId = orderService.setService(orderVo);
        resultVo.setCode(ResultState.SUCCESS.getCode())
                .setMsg(ResultState.SUCCESS.getMsg())
                .setResult(orderId);
        return resultVo;
    }

    /**
     * 支付完成
     * @param payResult
     * @return
     */
    @PostMapping("/pay")
    public ResultVo<PayResultVo> pay(@RequestBody PayResultVo payResult){
        ResultVo<PayResultVo> resultVo = new ResultVo<>();
        PayResultVo result = orderService.payFinishedService(payResult);
        resultVo.setCode(ResultState.SUCCESS.getCode())
                .setMsg(ResultState.SUCCESS.getMsg())
                .setResult(result);
        return resultVo;
    }

    /**
     * 获取支付后详情信息
     * @param payResult
     * @return
     */
    @PostMapping("/getDetail")
    public ResultVo<OrderVo> getDetail(@RequestBody PayResultVo payResult){
        ResultVo<OrderVo> resultVo = new ResultVo<>();
        OrderVo result = orderService.getDetailService(payResult.getOrderId());
        resultVo.setCode(ResultState.SUCCESS.getCode())
                .setMsg(ResultState.SUCCESS.getMsg())
                .setResult(result);
        return resultVo;
    }

}
