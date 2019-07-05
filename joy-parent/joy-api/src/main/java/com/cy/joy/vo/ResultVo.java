package com.cy.joy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 响应对象
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ResultVo<T> {

    private Integer code;
    private String msg;
    private Integer currentUserId;
    private T result;

}
