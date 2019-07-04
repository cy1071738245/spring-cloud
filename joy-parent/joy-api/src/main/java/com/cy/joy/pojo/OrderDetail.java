package com.cy.joy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderDetail implements Serializable {

    private Integer detailId;
    private String detailOrder;
    private Integer detailGoods;
    private Double detailPrice;
    private Integer detailNum;

}
