package com.yk726.myweb.topic;

import java.util.ArrayList;

import com.yk726.myweb.command.TopicVO;

public interface TopicService {
	
	public int regist(TopicVO vo);
	public ArrayList<TopicVO> getList();
	public ArrayList<TopicVO> getList1();
	public TopicVO getModify(int topic_bno);
	public TopicVO getDetail(int topic_bno);
	public int update(TopicVO vo);
	public int delete(int topic_bno);
}
