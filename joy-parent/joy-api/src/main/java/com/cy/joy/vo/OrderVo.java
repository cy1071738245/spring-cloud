package com.cy.joy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderVo {

    private Integer typeNum;
    private String tableNum;
    private Integer diningState;
    private Integer tablewareNum;
    private String remark;
    private Double orderTotalPrice;
    private List<OrderDetailVo> orderDetailList;

}
