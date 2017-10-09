package org.gzhmc.report4gzhmc.service;

import org.gzhmc.common.base.BaseService;
import org.gzhmc.report4gzhmc.model.Grade;

/**
 * @author stshen
 *
 *         2017年11月8日
 */
public interface GradeService extends BaseService<Grade> {
	public int getByStuGradeId(int cGradeId);

	public int getByGraExamGradeId(int cGradeId);

	public int getByteaGradeGradeId(int cGradeId);
}
