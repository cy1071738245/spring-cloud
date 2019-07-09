package com.cy.joy.pojo;

import com.cy.joy.vo.CartVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CartsParams {

    List<CartVo> carts;
    String token;

}
