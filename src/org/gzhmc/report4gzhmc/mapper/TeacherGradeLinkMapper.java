package org.gzhmc.report4gzhmc.mapper;

import java.util.List;

import org.gzhmc.report4gzhmc.model.TeacherGradeLink;

/**
 * @author stshen
 *
 * 2017年9月25日
 */
public interface TeacherGradeLinkMapper {
  public List<TeacherGradeLink> getAllTeaGra();
  public List<TeacherGradeLink> getAllTeaGraByTeaId(int cTeacherId);
}
