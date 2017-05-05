package org.gzhmc.report4gzhmc.mapper;

import org.gzhmc.common.base.BaseMapper;
import org.gzhmc.report4gzhmc.model.Teacher;

public interface TeacherMapper extends BaseMapper<Teacher>{
	public Teacher getByTeacherId(String teacherId);
   
}