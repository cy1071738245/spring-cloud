package com.cy.joy.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PayResultVo {

    private String orderId;
    private Double orderTotalPrice;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
