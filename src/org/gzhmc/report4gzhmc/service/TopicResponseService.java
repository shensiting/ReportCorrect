package org.gzhmc.report4gzhmc.service;

import java.util.List;

import org.gzhmc.report4gzhmc.model.TopicResponse;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
public interface TopicResponseService {
	
	int deleteByPrimaryKey(Long cId);

	int insert(TopicResponse record);

	int insertSelective(TopicResponse record);

	TopicResponse selectByPrimaryKey(Long cId);

	int updateByPrimaryKeySelective(TopicResponse record);

	int updateStatusByPrimaryKey(Long cId);

	int updateByPrimaryKey(TopicResponse record);

	List<TopicResponse> getByTopicId(int cTopicId);
}
