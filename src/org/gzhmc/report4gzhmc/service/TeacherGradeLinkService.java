package org.gzhmc.report4gzhmc.service;

import java.util.List;

import org.gzhmc.report4gzhmc.model.TeacherGradeLink;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
public interface TeacherGradeLinkService {

	public List<TeacherGradeLink> getAllTeaGra();

	public List<TeacherGradeLink> getAllTeaGraByTeaId(int cTeacherId);
}
