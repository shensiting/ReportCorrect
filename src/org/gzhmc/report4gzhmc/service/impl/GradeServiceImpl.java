package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.GradeMapper;
import org.gzhmc.report4gzhmc.model.Grade;
import org.gzhmc.report4gzhmc.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class GradeServiceImpl implements GradeService{

	@Autowired
	GradeMapper gradeMapper;
	
	@Override
	public int add(Grade t) {		
		return gradeMapper.add(t);
	}

	@Override
	public int delete(int cId) {		
		return gradeMapper.delete(cId);
	}

	@Override
	public int update(Grade t) {		
		return gradeMapper.update(t);
	}

	@Override
	public int updateSelective(Grade t) {		
		return gradeMapper.updateSelective(t);
	}

	@Override
	public Grade getById(int cId) {	
		return gradeMapper.getById(cId);
	}

	@Override
	public List<Grade> getAll() {
		List<Grade> grades=gradeMapper.getAll();
		return grades;
	}

	@Override
	public int getByStuGradeId(int cGradeId) {		
		return gradeMapper.getByStuGradeId(cGradeId);
	}

	@Override
	public int getByGraExamGradeId(int cGradeId) {
		
		return gradeMapper.getByGraExamGradeId(cGradeId);
	}

	@Override
	public int getByteaGradeGradeId(int cGradeId) {		
		return gradeMapper.getByteaGradeGradeId(cGradeId);
	}

}
