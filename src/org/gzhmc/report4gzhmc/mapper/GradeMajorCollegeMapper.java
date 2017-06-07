package org.gzhmc.report4gzhmc.mapper;

import java.util.List;

import org.gzhmc.report4gzhmc.model.GradeMajorCollege;

public interface GradeMajorCollegeMapper {
  
	public List<GradeMajorCollege> getAll();
	public GradeMajorCollege getById(int cId); 
	public List<GradeMajorCollege> getByCollegeId(int cCollegeId);
}
