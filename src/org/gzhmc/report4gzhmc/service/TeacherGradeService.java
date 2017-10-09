package org.gzhmc.report4gzhmc.service;

import java.util.List;

import org.gzhmc.common.base.BaseService;
import org.gzhmc.report4gzhmc.model.TeacherGrade;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
public interface TeacherGradeService extends BaseService<TeacherGrade>{
	
	public int updateByPrimaryKeySelective(TeacherGrade teacherGrade);

	public List<TeacherGrade> getAllTeaGraByTeaIdAndStatu(int cTeacherId);

	public int getByTeaIdAndGradeId(int cTeacherId, int cGradeId);

	public int getCountByGradeId(int cGradeId);
}
