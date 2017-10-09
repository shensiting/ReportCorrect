package org.gzhmc.report4gzhmc.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.gzhmc.common.base.BaseMapper;

import org.gzhmc.report4gzhmc.model.Student;

public interface StudentMapper extends BaseMapper<Student> {
	
	public int getByStudentNumber(String cStudentNumber);

	public List<Student> getNoSubmitStuByGradeIdAndExamId(@Param("cExperimentId") int cExperimentId,@Param("cGradeId") int cGradeId);

	public int getCountAll(int cGradeId);
}