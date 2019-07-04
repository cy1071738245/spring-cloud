package com.cy.joy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态
 */
@Getter
@AllArgsConstructor
public enum UsersState {

    NORMAL(1,"正常"),
    FORBIDDEN(2,"禁用"),
    DELETE(-1,"删除"),
    ;

    private Integer code;
    private String msg;

}
