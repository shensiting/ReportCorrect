package org.gzhmc.report4gzhmc.mapper;

import java.util.List;

import org.gzhmc.common.base.BaseMapper;
import org.gzhmc.report4gzhmc.model.TeacherGrade;

public interface TeacherGradeMapper extends BaseMapper<TeacherGrade> {
	public int updateByPrimaryKeySelective(TeacherGrade teacherGrade);

	public List<TeacherGrade> getAllTeaGraByTeaIdAndStatu(int cTeacherId);

	public int getByTeaIdAndGradeId(int cTeacherId, int cGradeId);

	public int getCountByGradeId(int cGradeId);
}