package com.cy.joy.mapper;

import com.cy.joy.pojo.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {

	List<Goods> list();

	List<Goods> listByType(@Param("goodsType") Integer goodsType);

	Goods get(@Param("goodId") Integer goodId);

}
