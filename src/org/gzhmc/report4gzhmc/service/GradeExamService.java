package org.gzhmc.report4gzhmc.service;

import java.util.List;

import org.gzhmc.report4gzhmc.model.GradeExam;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
public interface GradeExamService {
	public int deleteByPrimaryKey(int cId);

	public List<GradeExam> getAll();

	public List<GradeExam> getByGradeId(int cGradeId, int cSubmitForm);

	public GradeExam getById(int cId);

	public List<GradeExam> getByTeaGradeId(int cGradeId);

	public int add(GradeExam gradeExam);

	public int updateSelective(GradeExam gradeExam);

	public int updateSubmitForm(GradeExam gradeExam);

	public int getReportNumByExperimentIdandGradeId(int cGradeId, int cExperimentId);

	public GradeExam getByStuIdAndExamId(int cUserId, int cExperimentId);

	public int getByExamIdAndGradeId(int cExperimentId, int cGradeId);
}
