package org.gzhmc.report4gzhmc.service;

import java.util.List;

import org.gzhmc.report4gzhmc.model.GradeMajorCollege;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
public interface GradeMajorCollegeService {

	public List<GradeMajorCollege> getAll();

	public GradeMajorCollege getById(int cId);

	public List<GradeMajorCollege> getByCollegeId(int cCollegeId);
}
