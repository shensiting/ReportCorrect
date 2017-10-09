package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.TeacherGradeLinkMapper;
import org.gzhmc.report4gzhmc.model.TeacherGradeLink;
import org.gzhmc.report4gzhmc.service.TeacherGradeLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class TeacherGradeLinkServiceImpl implements TeacherGradeLinkService {

	@Autowired
	TeacherGradeLinkMapper teacherGradeLinkMapper;
	
	@Override
	public List<TeacherGradeLink> getAllTeaGra() {
		List<TeacherGradeLink> teacherGradeLinks=teacherGradeLinkMapper.getAllTeaGra();
		return teacherGradeLinks;
	}

	@Override
	public List<TeacherGradeLink> getAllTeaGraByTeaId(int cTeacherId) {
		List<TeacherGradeLink> teacherGradeLinks=teacherGradeLinkMapper.getAllTeaGraByTeaId(cTeacherId);
		return teacherGradeLinks;
	}

}
