package com.cy.joy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderDetailVo {

    private Integer orderDetailGoodId;
    private String orderDetailName;
    private Integer orderDetailNum;
    private Double orderDetailPrice;
    private String orderDetailImg;

}
