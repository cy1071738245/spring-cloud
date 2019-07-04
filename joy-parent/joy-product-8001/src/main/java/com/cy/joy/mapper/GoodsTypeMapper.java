package com.cy.joy.mapper;

import com.cy.joy.pojo.GoodsType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsTypeMapper {

    List<GoodsType> list();

}
