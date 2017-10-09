package org.gzhmc.report4gzhmc.service;

import org.gzhmc.common.base.BaseService;
import org.gzhmc.report4gzhmc.model.Teacher;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
public interface TeacherService extends BaseService<Teacher>{
	public Teacher getByTeacherId(String teacherId);
}
