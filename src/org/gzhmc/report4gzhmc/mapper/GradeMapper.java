package org.gzhmc.report4gzhmc.mapper;

import org.gzhmc.common.base.BaseMapper;
import org.gzhmc.report4gzhmc.model.Grade;

public interface GradeMapper extends BaseMapper<Grade> {
    public int  getByStuGradeId(int cGradeId);
    public int  getByGraExamGradeId(int cGradeId);
    public int  getByteaGradeGradeId(int cGradeId);
	
}