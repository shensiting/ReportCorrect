package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.TeacherGradeMapper;
import org.gzhmc.report4gzhmc.model.TeacherGrade;
import org.gzhmc.report4gzhmc.service.TeacherGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class TeacherGradeServiceImpl implements TeacherGradeService {

	@Autowired
	TeacherGradeMapper teacherGradeMapper;
	
	@Override
	public int add(TeacherGrade t) {		
		return teacherGradeMapper.add(t);
	}

	@Override
	public int delete(int cId) {
		return teacherGradeMapper.delete(cId);
	}

	@Override
	public int update(TeacherGrade t) {
		return teacherGradeMapper.update(t);
	}

	@Override
	public int updateSelective(TeacherGrade t) {
		return teacherGradeMapper.updateSelective(t);
	}

	@Override
	public TeacherGrade getById(int cId) {
		return teacherGradeMapper.getById(cId);
	}

	@Override
	public List<TeacherGrade> getAll() {
        List<TeacherGrade> teacherGrades=teacherGradeMapper.getAll();
		return teacherGrades;
	}

	@Override
	public int updateByPrimaryKeySelective(TeacherGrade teacherGrade) {
		return teacherGradeMapper.updateByPrimaryKeySelective(teacherGrade);
	}

	@Override
	public List<TeacherGrade> getAllTeaGraByTeaIdAndStatu(int cTeacherId) {
		List<TeacherGrade> teacherGrades=teacherGradeMapper.getAllTeaGraByTeaIdAndStatu(cTeacherId);
		return teacherGrades;
	}

	@Override
	public int getByTeaIdAndGradeId(int cTeacherId, int cGradeId) {
		return teacherGradeMapper.getByTeaIdAndGradeId(cTeacherId, cGradeId);
	}

	@Override
	public int getCountByGradeId(int cGradeId) {
		return teacherGradeMapper.getCountByGradeId(cGradeId);
	}

}
