package com.cy.joy.mapper;

import com.cy.joy.vo.OrderDetailVo;
import com.cy.joy.vo.OrderVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    Integer setOrder(@Param("orderId") String orderId, @Param("orderVo") OrderVo orderVo);

    Integer setOrderDetail(@Param("orderId") String orderId, @Param("orderDetailVo") OrderDetailVo orderDetailVo);

    Integer payFinished(@Param("orderId") String orderId);

    Double getOrderPrice(@Param("orderId") String orderId);

    List<OrderDetailVo> getDetail(@Param("orderId") String orderId);

}
