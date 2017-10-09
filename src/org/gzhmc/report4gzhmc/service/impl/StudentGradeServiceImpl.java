package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.StudentGradeMapper;
import org.gzhmc.report4gzhmc.model.StudentGrade;
import org.gzhmc.report4gzhmc.service.StudentGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class StudentGradeServiceImpl implements StudentGradeService{

	@Autowired
	StudentGradeMapper studentGradeMapper;
	
	@Override
	public List<StudentGrade> getAll() {
		List<StudentGrade> studentGrades=studentGradeMapper.getAll();
		return studentGrades;
	}

	@Override
	public StudentGrade getById(int cId) {
		StudentGrade studentGrade=studentGradeMapper.getById(cId);
		return studentGrade;
	}

}
