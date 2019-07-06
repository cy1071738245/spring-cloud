package com.cy.joy.service;

import com.cy.joy.vo.CartVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "JOY-CART")
public interface CartClientService {

    @PostMapping("/cart/MergerOfCarts")
    List<CartVo> MergerOfCartsService(@RequestBody(required = false) List<CartVo> carts, @RequestParam("userId") Integer userId);

    @GetMapping("/cart/get")
    List<CartVo> get(@RequestParam("userId") Integer userId);

}
