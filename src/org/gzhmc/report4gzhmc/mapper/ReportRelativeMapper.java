package org.gzhmc.report4gzhmc.mapper;

import java.util.List;

import org.gzhmc.report4gzhmc.model.ReportRelative;

public interface ReportRelativeMapper {

	public List<ReportRelative> getAll();

	public ReportRelative getById(int cId);

	public List<ReportRelative> getExitScoreAll();

	public List<ReportRelative> getByGradeId(int cGradeId);
	
	public List<ReportRelative> getByStudentId(int cStudentId);

	public List<ReportRelative> getByExperimentId(int cExperimentTextId);
	
	public List<ReportRelative> getScoreByExperimentIdandGradeId(int cExperimentTextId,int gradeId);
	
	public List<ReportRelative> getExitScoreByExperimentIdandGradeId(int cExperimentTextId,int gradeId);
	
	public List<ReportRelative> getExitScoreByExperimentId(int[] cExperimentTextIds);

	public List<ReportRelative> getExitScoreAllByStudentId(int cStudentId);

	public List<ReportRelative> getReportLinkExperimentByStuId(int cStudentId);
	
	public List<ReportRelative> getExperimentIdandGradeId();
	
	public List<ReportRelative> getAllBystatu();
		
	public List<ReportRelative> getByManyId(int[] cExperimentTextIds);

}
