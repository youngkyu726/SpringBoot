package com.yk726.myweb.topic;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.yk726.myweb.command.TopicVO;

@Mapper
public interface TopicMapper {

	public int regist(TopicVO vo);
	public ArrayList<TopicVO> getList();
	public ArrayList<TopicVO> getList1();
	public TopicVO getModify(int topic_bno);
	public TopicVO getDetail(int topic_bno);
	public int update(TopicVO vo);
	public int delete(int topic_bno);
}
