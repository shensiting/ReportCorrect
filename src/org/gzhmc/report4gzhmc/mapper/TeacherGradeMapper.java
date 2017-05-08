package org.gzhmc.report4gzhmc.mapper;

import org.gzhmc.report4gzhmc.model.TeacherGrade;

public interface TeacherGradeMapper {
    int deleteByPrimaryKey(Integer cId);

    int insert(TeacherGrade record);

    int insertSelective(TeacherGrade record);

    TeacherGrade selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(TeacherGrade record);

    int updateByPrimaryKey(TeacherGrade record);
}