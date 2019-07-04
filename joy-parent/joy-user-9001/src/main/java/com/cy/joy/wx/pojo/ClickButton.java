package com.cy.joy.wx.pojo;

import lombok.Data;

@Data
public class ClickButton extends AbstractButton {

    private String type = "click";
    private String url;

}
