package org.gzhmc.report4gzhmc.mapper;

import org.gzhmc.common.base.BaseMapper;
import org.gzhmc.report4gzhmc.model.College;

public interface CollegeMapper extends BaseMapper<College> {
	// 方法名字解释：在Grade表中查找指定学院ID的班级的数目，以后的命名方式皆相同
	public int getByGradeCollegeId(int cCollegeId);

	public int getByTeacherCollegeId(int cCollegeId);

}