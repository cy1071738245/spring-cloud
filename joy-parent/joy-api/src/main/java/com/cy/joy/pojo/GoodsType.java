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
public class GoodsType implements Serializable {

    private Integer typeId;
    private String typeName;
    private Integer typePid;
    private Integer typeLv;
    private String typePath;
    private Integer typeState;

}
