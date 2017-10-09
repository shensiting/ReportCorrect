package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.StudentMapper;
import org.gzhmc.report4gzhmc.model.Student;
import org.gzhmc.report4gzhmc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentMapper studentMapper;
	
	@Override
	public int add(Student t) {		
		return studentMapper.add(t);
	}

	@Override
	public int delete(int cId) {		
		return studentMapper.delete(cId);
	}

	@Override
	public int update(Student t) {		
		return studentMapper.update(t);
	}

	@Override
	public int updateSelective(Student t) {		
		return studentMapper.updateSelective(t);
	}

	@Override
	public Student getById(int cId) {		
		return studentMapper.getById(cId);
	}

	@Override
	public List<Student> getAll() {
		List<Student> students=studentMapper.getAll();
		return students;
	}

	@Override
	public int getByStudentNumber(String cStudentNumber) {		
		return studentMapper.getByStudentNumber(cStudentNumber);
	}

	@Override
	public List<Student> getNoSubmitStuByGradeIdAndExamId(int cExperimentId, int cGradeId) {
		List<Student> students=studentMapper.getNoSubmitStuByGradeIdAndExamId(cExperimentId, cGradeId);
		return students;
	}

	@Override
	public int getCountAll(int cGradeId) {		
		return studentMapper.getCountAll(cGradeId);
	}

}
