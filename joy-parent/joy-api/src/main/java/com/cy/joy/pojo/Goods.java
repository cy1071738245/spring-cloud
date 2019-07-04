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
public class Goods implements Serializable {

    private Integer goodsId;
    private String goodsName;
    private Double goodsPrice;
    private Integer goodsNum;
    private Integer goodsType;
    private Integer goodsMemory;
    private String goodsColor;
    private String goodsImg;
    private Integer goodsState;
    private String goodsDesc;
    private Integer goodsVolume;

}
