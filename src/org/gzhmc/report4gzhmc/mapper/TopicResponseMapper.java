package org.gzhmc.report4gzhmc.mapper;

import java.util.List;

import org.gzhmc.report4gzhmc.model.TopicResponse;

public interface TopicResponseMapper {
	
	int deleteByPrimaryKey(Long cId);

	int insert(TopicResponse record);

	int insertSelective(TopicResponse record);

	TopicResponse selectByPrimaryKey(Long cId);

	int updateByPrimaryKeySelective(TopicResponse record);

	int updateStatusByPrimaryKey(Long cId);

	int updateByPrimaryKey(TopicResponse record);

	List<TopicResponse> getByTopicId(int cTopicId);
}