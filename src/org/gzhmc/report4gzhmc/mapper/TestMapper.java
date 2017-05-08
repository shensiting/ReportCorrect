package org.gzhmc.report4gzhmc.mapper;

import org.gzhmc.report4gzhmc.model.Test;

public interface TestMapper {
    int deleteByPrimaryKey(Integer cId);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);
}