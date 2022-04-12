package com.simple.basic.memo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.basic.command.MemoVO;

@Service("memoService")
public class MemoServiceImpl implements MemoService{

	@Autowired
	private MemoMapper memoMapper;
	
	@Override
	public String getTime() {
		return memoMapper.getTime();
	}

	@Override
	public void memoInsert(MemoVO vo) {
		
		memoMapper.memoInsert(vo);
	}

	@Override
	public ArrayList<MemoVO> getList() {
		return memoMapper.getList();
	}

}
