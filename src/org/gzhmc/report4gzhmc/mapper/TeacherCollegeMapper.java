package org.gzhmc.report4gzhmc.mapper;

import java.util.List;

import org.gzhmc.report4gzhmc.model.TeacherCollege;

public interface TeacherCollegeMapper {
public List<TeacherCollege> getAll();
public TeacherCollege getById(int cUserId);
public TeacherCollege getByTeacherId(int cTeacherId);

}
