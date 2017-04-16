package org.gzhmc.report4gzhmc.mapper;

import java.util.List;

import org.gzhmc.report4gzhmc.model.TeacherlinkExperiment;

public interface TeacherlinkExperimentMapper {
   public List<TeacherlinkExperiment> getAll();
   public TeacherlinkExperiment getById(int cId);
}
