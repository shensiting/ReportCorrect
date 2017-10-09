package org.gzhmc.report4gzhmc.service;

import java.util.List;

import org.gzhmc.report4gzhmc.model.StudentGrade;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
public interface StudentGradeService {
	
	public List<StudentGrade> getAll();

	public StudentGrade getById(int cId);
}
