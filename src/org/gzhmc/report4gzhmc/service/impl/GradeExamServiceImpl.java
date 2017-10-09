package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.GradeExamMapper;
import org.gzhmc.report4gzhmc.model.GradeExam;
import org.gzhmc.report4gzhmc.service.GradeExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class GradeExamServiceImpl implements GradeExamService{

	@Autowired
	GradeExamMapper gradeExamMapper;
	
	@Override
	public int deleteByPrimaryKey(int cId) {		
		return gradeExamMapper.deleteByPrimaryKey(cId);
	}

	@Override
	public List<GradeExam> getAll() {
		List<GradeExam> gradeExams=gradeExamMapper.getAll();
		return gradeExams;
	}

	@Override
	public List<GradeExam> getByGradeId(int cGradeId, int cSubmitForm) {
		List<GradeExam> gradeExams=gradeExamMapper.getByGradeId(cGradeId,cSubmitForm);
		return gradeExams;
	}

	@Override
	public GradeExam getById(int cId) {		
		return gradeExamMapper.getById(cId);
	}

	@Override
	public List<GradeExam> getByTeaGradeId(int cGradeId) {
		List<GradeExam> gradeExams=gradeExamMapper.getByTeaGradeId(cGradeId);
		return gradeExams;
	}

	@Override
	public int add(GradeExam gradeExam) {		
		return gradeExamMapper.add(gradeExam);
	}

	@Override
	public int updateSelective(GradeExam gradeExam) {
		
		return gradeExamMapper.updateSelective(gradeExam);
	}

	@Override
	public int updateSubmitForm(GradeExam gradeExam) {
		
		return gradeExamMapper.updateSubmitForm(gradeExam);
	}

	@Override
	public int getReportNumByExperimentIdandGradeId(int cGradeId, int cExperimentId) {
		
		return gradeExamMapper.getReportNumByExperimentIdandGradeId(cGradeId, cExperimentId);
	}

	@Override
	public GradeExam getByStuIdAndExamId(int cUserId, int cExperimentId) {
		GradeExam gradeExam=gradeExamMapper.getByStuIdAndExamId(cUserId, cExperimentId);
		return gradeExam;
	}

	@Override
	public int getByExamIdAndGradeId(int cExperimentId, int cGradeId) {
		
		return gradeExamMapper.getByExamIdAndGradeId(cExperimentId, cGradeId);
	}

}
