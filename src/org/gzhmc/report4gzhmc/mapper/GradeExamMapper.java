package org.gzhmc.report4gzhmc.mapper;

import java.util.List;

import org.gzhmc.common.base.BaseMapper;
import org.gzhmc.report4gzhmc.model.GradeExam;

public interface GradeExamMapper{
   public int deleteByPrimaryKey(int cId);
   public List<GradeExam> getAll();
   public List<GradeExam> getByGradeId(int cGradeId);
   public GradeExam getById(int cId);
   public List<GradeExam> getByTeaGradeId(int cGradeId);
   public int add(GradeExam gradeExam);
   public int updateSelective(GradeExam gradeExam);
   public int getReportNumByExperimentIdandGradeId(int cGradeId,int cExperimentId);
}