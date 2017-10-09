package org.gzhmc.report4gzhmc.service;

import org.gzhmc.common.base.BaseService;
import org.gzhmc.report4gzhmc.model.Test;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
public interface TestService extends BaseService<Test>{
	
	public int updateByPrimaryKey(Test test);

	public int getByExamTestId(int cTestId);
}
