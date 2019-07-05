package com.cy.joy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CartVo {

    private Integer proId;
    private String proName;
    private Integer proNumber;
    private Double proTotalPrice;
    private Double proPrice;
    private Integer currentUserId;

}
