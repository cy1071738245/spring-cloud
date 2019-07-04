package com.cy.joy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品状态
 */
@Getter
@AllArgsConstructor
public enum GoodsState {

    NORMAL(1,"正常"),
    HOT(2,"热卖"),
    DELETE(-1,"删除"),
    ;

    private Integer code;
    private String msg;

}
