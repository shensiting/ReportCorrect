package org.gzhmc.report4gzhmc.service;

import java.util.List;

import org.gzhmc.report4gzhmc.model.ReportRelative;

/**
 * @author stshen
 *
 *         2017年11月8日
 */
public interface ReportRelativeService {
	
	public List<ReportRelative> getAll();

	public ReportRelative getById(int cId);

	public ReportRelative getPostilById(int cId);

	public List<ReportRelative> getExitScoreAll();

	public List<ReportRelative> getByGradeId(int cGradeId, int cSubmitForm);

	public List<ReportRelative> getByStudentId(int cStudentId);

	public List<ReportRelative> getScoreByStudentId(int cStudentId, int cSubmitForm);

	public List<ReportRelative> getScoreByTeacherId(int cTeacherId, int cSubmitForm);

	public List<ReportRelative> getByExperimentId(int cExperimentTextId);

	public List<ReportRelative> getScoreByExperimentIdandGradeId(int cExperimentTextId, int gradeId);

	public List<ReportRelative> getExitScoreByExperimentIdandGradeId(int cExperimentTextId, int gradeId,int cSubmitForm);

	public List<ReportRelative> getExitScoreByExperimentId(int[] cExperimentTextIds);

	public List<ReportRelative> getExitScoreAllByStudentId(int cStudentId);

	public List<ReportRelative> getReportLinkExperimentByStuIdAndSubmitForm(int cStudentId, int cSubmitForm);

	public List<ReportRelative> getExperimentIdandGradeId();

	public List<ReportRelative> getAllBystatu();

	public List<ReportRelative> getByManyId(int[] cExperimentTextIds);

	public List<ReportRelative> getByExamIdandGradeId(int cExperimentTextId, int gradeId);
}
