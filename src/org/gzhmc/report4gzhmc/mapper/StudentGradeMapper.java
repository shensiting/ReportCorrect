package org.gzhmc.report4gzhmc.mapper;

import java.util.List;

import org.gzhmc.report4gzhmc.model.StudentGrade;

public interface StudentGradeMapper {
public  List<StudentGrade> getAll();
public StudentGrade getById(int cId);
}
