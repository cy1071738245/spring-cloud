package com.cy.joy.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Order implements Serializable {

    private String orderId;
    private Integer orderUser;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date orderDate;
    private Double orderPrice;
    private Integer orderState;
    private String orderUserName;
    private String orderPhone;
    private String orderAddress;
    private String orderExpressNo;

}
