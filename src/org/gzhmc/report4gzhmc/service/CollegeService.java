package org.gzhmc.report4gzhmc.service;

import org.gzhmc.common.base.BaseService;
import org.gzhmc.report4gzhmc.model.College;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
public interface CollegeService extends BaseService<College>{
	public int getByGradeCollegeId(int cCollegeId);
	public int getByTeacherCollegeId(int cCollegeId);
}
