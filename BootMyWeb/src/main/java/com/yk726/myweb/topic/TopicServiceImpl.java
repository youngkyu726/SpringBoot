package com.yk726.myweb.topic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yk726.myweb.command.TopicVO;

@Service("topicService")
public class TopicServiceImpl implements TopicService{
	
	@Autowired
	private TopicMapper topicMapper;

	@Override
	public int regist(TopicVO vo) {
		return topicMapper.regist(vo);
	}

	@Override
	public ArrayList<TopicVO> getList() {
		return topicMapper.getList();
	}

	@Override
	public TopicVO getModify(int topic_bno) {
		return topicMapper.getModify(topic_bno);
	}

	@Override
	public int update(TopicVO vo) {
		return topicMapper.update(vo);
	}

	@Override
	public int delete(int topic_bno) {
		return topicMapper.delete(topic_bno);
	}

	@Override
	public ArrayList<TopicVO> getList1() {
		return topicMapper.getList1();
	}

	@Override
	public TopicVO getDetail(int topic_bno) {
		return topicMapper.getDetail(topic_bno);
	}

}
