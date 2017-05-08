package org.gzhmc.report4gzhmc.mapper;

import org.gzhmc.report4gzhmc.model.TopicResponse;

public interface TopicResponseMapper {
    int deleteByPrimaryKey(Long cId);

    int insert(TopicResponse record);

    int insertSelective(TopicResponse record);

    TopicResponse selectByPrimaryKey(Long cId);

    int updateByPrimaryKeySelective(TopicResponse record);

    int updateByPrimaryKeyWithBLOBs(TopicResponse record);

    int updateByPrimaryKey(TopicResponse record);
}