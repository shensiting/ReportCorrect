package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.TopicResponseMapper;
import org.gzhmc.report4gzhmc.model.TopicResponse;
import org.gzhmc.report4gzhmc.service.TopicResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class TopicResponseServiceImpl implements TopicResponseService {

	@Autowired
	TopicResponseMapper topicResponseMapper;
	
	@Override
	public int deleteByPrimaryKey(Long cId) {		
		return topicResponseMapper.deleteByPrimaryKey(cId);
	}

	@Override
	public int insert(TopicResponse record) {		
		return topicResponseMapper.insert(record);
	}

	@Override
	public int insertSelective(TopicResponse record) {		
		return topicResponseMapper.insertSelective(record);
	}

	@Override
	public TopicResponse selectByPrimaryKey(Long cId) {		
		return topicResponseMapper.selectByPrimaryKey(cId);
	}

	@Override
	public int updateByPrimaryKeySelective(TopicResponse record) {		
		return topicResponseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateStatusByPrimaryKey(Long cId) {		
		return topicResponseMapper.updateStatusByPrimaryKey(cId);
	}

	@Override
	public int updateByPrimaryKey(TopicResponse record) {		
		return topicResponseMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TopicResponse> getByTopicId(int cTopicId) {
		List<TopicResponse> topicResponses=topicResponseMapper.getByTopicId(cTopicId);
		return topicResponses;
	}

}
