package org.gzhmc.report4gzhmc.mapper;

import java.util.List;

import org.gzhmc.common.base.BaseMapper;
import org.gzhmc.report4gzhmc.model.TeacherExperimental;

public interface TeacherExperimentalMapper extends BaseMapper<TeacherExperimental> {
	public List<TeacherExperimental> getByTeacherId(int cTeacherId);

	public int getCountByTeacherId(int cTeacherId);
}