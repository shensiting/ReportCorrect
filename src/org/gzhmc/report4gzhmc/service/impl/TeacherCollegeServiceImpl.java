package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.TeacherCollegeMapper;
import org.gzhmc.report4gzhmc.model.TeacherCollege;
import org.gzhmc.report4gzhmc.service.TeacherCollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class TeacherCollegeServiceImpl implements TeacherCollegeService {

	@Autowired
	TeacherCollegeMapper teacherCollegeMapper;
	
	@Override
	public List<TeacherCollege> getAll() {
		List<TeacherCollege> teacherColleges=teacherCollegeMapper.getAll();
		return teacherColleges;
	}

	@Override
	public TeacherCollege getById(int cUserId) {
		TeacherCollege teacherCollege=teacherCollegeMapper.getById(cUserId);
		return teacherCollege;
	}

	@Override
	public TeacherCollege getByTeacherId(int cTeacherId) {
		TeacherCollege teacherCollege=teacherCollegeMapper.getByTeacherId(cTeacherId);
		return teacherCollege;
	}

}
