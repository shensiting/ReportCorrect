package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.ReportRelativeMapper;
import org.gzhmc.report4gzhmc.model.ReportRelative;
import org.gzhmc.report4gzhmc.service.ReportRelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class ReportRelativeServiceImpl implements ReportRelativeService{

	@Autowired
	ReportRelativeMapper reportRelativeMapper;
	
	@Override
	public List<ReportRelative> getAll() {
		List<ReportRelative> reportRelatives=reportRelativeMapper.getAll();
		return reportRelatives;
	}

	@Override
	public ReportRelative getById(int cId) {
		ReportRelative reportRelative=reportRelativeMapper.getById(cId);
		return reportRelative;
	}

	@Override
	public ReportRelative getPostilById(int cId) {
		ReportRelative reportRelative=reportRelativeMapper.getPostilById(cId);
		return reportRelative;
	}

	@Override
	public List<ReportRelative> getExitScoreAll() {
		List<ReportRelative> reportRelatives=reportRelativeMapper.getExitScoreAll();
		return reportRelatives;
	}

	@Override
	public List<ReportRelative> getByGradeId(int cGradeId, int cSubmitForm) {
		List<ReportRelative> reportRelatives=reportRelativeMapper.getByGradeId(cGradeId, cSubmitForm);
		return reportRelatives;
	}

	@Override
	public List<ReportRelative> getByStudentId(int cStudentId) {
		List<ReportRelative> reportRelatives=reportRelativeMapper.getByStudentId(cStudentId);
		return reportRelatives;
	}

	@Override
	public List<ReportRelative> getScoreByStudentId(int cStudentId, int cSubmitForm) {
		List<ReportRelative> reportRelatives=reportRelativeMapper.getScoreByStudentId(cStudentId, cSubmitForm);
		return reportRelatives;
	}

	@Override
	public List<ReportRelative> getScoreByTeacherId(int cTeacherId, int cSubmitForm) {
		List<ReportRelative> reportRelatives=reportRelativeMapper.getScoreByTeacherId(cTeacherId, cSubmitForm);
		return reportRelatives;
	}

	@Override
	public List<ReportRelative> getByExperimentId(int cExperimentTextId) {
		List<ReportRelative> reportRelatives=reportRelativeMapper.getByExperimentId(cExperimentTextId);
		return reportRelatives;
	}

	@Override
	public List<ReportRelative> getScoreByExperimentIdandGradeId(int cExperimentTextId, int gradeId) {
		List<ReportRelative> reportRelatives=reportRelativeMapper.getScoreByExperimentIdandGradeId(cExperimentTextId,gradeId);
		return reportRelatives;
	}

	@Override
	public List<ReportRelative> getExitScoreByExperimentIdandGradeId(int cExperimentTextId, int gradeId, int cSubmitForm) {
		List<ReportRelative> reportRelatives=reportRelativeMapper.getExitScoreByExperimentIdandGradeId(cExperimentTextId, gradeId, cSubmitForm);
		return reportRelatives;
	}

	@Override
	public List<ReportRelative> getExitScoreByExperimentId(int[] cExperimentTextIds) {
		List<ReportRelative> reportRelatives=reportRelativeMapper.getExitScoreByExperimentId(cExperimentTextIds);
		return reportRelatives;
	}

	@Override
	public List<ReportRelative> getExitScoreAllByStudentId(int cStudentId) {
		List<ReportRelative> reportRelatives=reportRelativeMapper.getExitScoreAllByStudentId(cStudentId);
		return reportRelatives;
	}

	@Override
	public List<ReportRelative> getReportLinkExperimentByStuIdAndSubmitForm(int cStudentId, int cSubmitForm) {
		List<ReportRelative> reportRelatives=reportRelativeMapper.getReportLinkExperimentByStuIdAndSubmitForm(cStudentId, cSubmitForm);
		return reportRelatives;
	}

	@Override
	public List<ReportRelative> getExperimentIdandGradeId() {
		List<ReportRelative> reportRelatives=reportRelativeMapper.getExperimentIdandGradeId();
		return reportRelatives;
	}

	@Override
	public List<ReportRelative> getAllBystatu() {
		List<ReportRelative> reportRelatives=reportRelativeMapper.getAllBystatu();
		return reportRelatives;
	}

	@Override
	public List<ReportRelative> getByManyId(int[] cExperimentTextIds) {
		List<ReportRelative> reportRelatives=reportRelativeMapper.getByManyId(cExperimentTextIds);
		return reportRelatives;
	}

	@Override
	public List<ReportRelative> getByExamIdandGradeId(int cExperimentTextId, int gradeId) {
		List<ReportRelative> reportRelatives=reportRelativeMapper.getByExamIdandGradeId(cExperimentTextId, gradeId);
		return reportRelatives;
	}

}
