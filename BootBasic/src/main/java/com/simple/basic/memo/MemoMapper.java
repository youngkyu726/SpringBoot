package com.simple.basic.memo;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.simple.basic.command.MemoVO;

//마이바티스 xml의 추상메서드를 지정할 인터페이스 - com.simple.basic기준 패키지 하위로 작성
@Mapper //boot에서는 인터페이스에 @Mapper를 반드시 기술
public interface MemoMapper {
	
	public String getTime();
	public void memoInsert(MemoVO vo); //등록
	public ArrayList<MemoVO> getList();
}
