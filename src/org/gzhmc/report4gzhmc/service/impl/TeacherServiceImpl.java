package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.TeacherMapper;
import org.gzhmc.report4gzhmc.model.Teacher;
import org.gzhmc.report4gzhmc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherMapper teacherMapper;
	
	@Override
	public int add(Teacher t) {
		return teacherMapper.add(t);
	}

	@Override
	public int delete(int cId) {
		return teacherMapper.delete(cId);
	}

	@Override
	public int update(Teacher t) {
		return teacherMapper.update(t);
	}

	@Override
	public int updateSelective(Teacher t) {
		return teacherMapper.updateSelective(t);
	}

	@Override
	public Teacher getById(int cId) {
	    Teacher teacher=teacherMapper.getById(cId);
		return teacher;
	}

	@Override
	public List<Teacher> getAll() {
		List<Teacher> teachers=teacherMapper.getAll();
		return teachers;
	}

	@Override
	public Teacher getByTeacherId(String teacherId) {
		Teacher teacher=teacherMapper.getByTeacherId(teacherId);
		return teacher;
	}

}
