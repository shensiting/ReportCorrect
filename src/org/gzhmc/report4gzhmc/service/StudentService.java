package org.gzhmc.report4gzhmc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.gzhmc.common.base.BaseService;
import org.gzhmc.report4gzhmc.model.Student;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
public interface StudentService extends BaseService<Student>{
	
	public int getByStudentNumber(String cStudentNumber);

	public List<Student> getNoSubmitStuByGradeIdAndExamId( int cExperimentId,int cGradeId);

	public int getCountAll(int cGradeId);
}
