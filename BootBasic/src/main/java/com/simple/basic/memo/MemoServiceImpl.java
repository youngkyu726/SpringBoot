package com.simple.basic.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memoService")
public class MemoServiceImpl implements MemoService{

	@Autowired
	private MemoMapper memoMapper;
	
	@Override
	public String getTime() {
		return memoMapper.getTime();
	}

}
