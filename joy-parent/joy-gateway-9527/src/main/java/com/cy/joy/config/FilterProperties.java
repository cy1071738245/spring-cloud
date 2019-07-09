package com.cy.joy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liangtong.
 */
@Data
@Component
@ConfigurationProperties(prefix="sc.filter")
public class FilterProperties {

    //允许访问路径集合
    private List<String> allowPaths;
}
