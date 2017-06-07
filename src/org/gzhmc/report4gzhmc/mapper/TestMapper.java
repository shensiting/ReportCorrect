package org.gzhmc.report4gzhmc.mapper;

import java.util.List;

import org.gzhmc.common.base.BaseMapper;
import org.gzhmc.report4gzhmc.model.Test;

public interface TestMapper extends BaseMapper<Test>{
	public int updateByPrimaryKey(Test test);
	public int getByExamTestId(int cTestId);
}