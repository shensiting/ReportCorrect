package org.gzhmc.report4gzhmc.mapper;

import org.gzhmc.report4gzhmc.model.GradeExam;

public interface GradeExamMapper {
    int deleteByPrimaryKey(Integer cId);

    int insert(GradeExam record);

    int insertSelective(GradeExam record);

    GradeExam selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(GradeExam record);

    int updateByPrimaryKey(GradeExam record);
}