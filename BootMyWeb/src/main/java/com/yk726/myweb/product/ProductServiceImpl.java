package com.yk726.myweb.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yk726.myweb.command.ProductVO;

@Service("productService")
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public int regist(ProductVO vo) {
		
		return productMapper.regist(vo);
	}

}
