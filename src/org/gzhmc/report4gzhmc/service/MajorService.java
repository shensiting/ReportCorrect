package org.gzhmc.report4gzhmc.service;

import org.gzhmc.common.base.BaseService;
import org.gzhmc.report4gzhmc.model.Major;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
public interface MajorService extends BaseService<Major>{
	public int getByGradeMajorId(int cMajorId);
}
