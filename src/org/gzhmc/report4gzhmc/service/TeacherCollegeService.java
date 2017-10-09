package org.gzhmc.report4gzhmc.service;

import java.util.List;

import org.gzhmc.report4gzhmc.model.TeacherCollege;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
public interface TeacherCollegeService {
	
	public List<TeacherCollege> getAll();

	public TeacherCollege getById(int cUserId);

	public TeacherCollege getByTeacherId(int cTeacherId);
}
