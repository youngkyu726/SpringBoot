package com.yk726.myweb.product;

import org.apache.ibatis.annotations.Mapper;

import com.yk726.myweb.command.ProductVO;

@Mapper //필수
public interface ProductMapper {
	
	public int regist(ProductVO vo);//등록
}
