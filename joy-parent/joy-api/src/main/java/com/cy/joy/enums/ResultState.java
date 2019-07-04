package com.cy.joy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应状态
 */
@Getter
@AllArgsConstructor
public enum ResultState {

    SUCCESS(0, "成功"),
    ERROR(1, "失败"),
    ;

    private Integer code;
    private String msg;

}
