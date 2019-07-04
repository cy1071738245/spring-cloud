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
public class Cart implements Serializable {

    private Integer cartId;
    private Integer cartGoods;
    private Integer cartNum;
    private Double cartPrice;
    private Integer cartUser;

}
