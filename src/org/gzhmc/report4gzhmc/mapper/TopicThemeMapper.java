package org.gzhmc.report4gzhmc.mapper;

import org.gzhmc.report4gzhmc.model.TopicTheme;

public interface TopicThemeMapper {
    int deleteByPrimaryKey(Integer cId);

    int insert(TopicTheme record);

    int insertSelective(TopicTheme record);

    TopicTheme selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(TopicTheme record);

    int updateByPrimaryKeyWithBLOBs(TopicTheme record);

    int updateByPrimaryKey(TopicTheme record);
}