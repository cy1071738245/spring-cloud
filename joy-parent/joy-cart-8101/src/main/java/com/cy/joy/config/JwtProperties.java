package com.cy.joy.config;

import com.cy.joy.util.RasUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

/**
 * Created by cy.
 */
@Data
@Component
@ConfigurationProperties(prefix="sc.jwt")
public class JwtProperties {

    private String pubKeyPath;      // 公钥路径
    private PublicKey publicKey;    // 公钥对象

    //进行初始化操作
    @PostConstruct
    public void init(){
        try {
            //获得对应对象
            this.publicKey = RasUtils.getPublicKey(this.pubKeyPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
