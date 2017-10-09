package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.GradeMajorCollegeMapper;
import org.gzhmc.report4gzhmc.model.GradeMajorCollege;
import org.gzhmc.report4gzhmc.service.GradeMajorCollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 *         2017年11月8日
 */
@Service
public class GradeMajorCollegeServiceImpl implements GradeMajorCollegeService {

	@Autowired
	GradeMajorCollegeMapper gradeMajorCollegeMapper;

	@Override
	public List<GradeMajorCollege> getAll() {
		List<GradeMajorCollege> gradeMajorColleges=gradeMajorCollegeMapper.getAll();
		return gradeMajorColleges;
	}

	@Override
	public GradeMajorCollege getById(int cId) {
		GradeMajorCollege gradeMajorCollege=gradeMajorCollegeMapper.getById(cId);
		return gradeMajorCollege;
	}

	@Override
	public List<GradeMajorCollege> getByCollegeId(int cCollegeId) {
		List<GradeMajorCollege> gradeMajorColleges=gradeMajorCollegeMapper.getByCollegeId(cCollegeId);
		return gradeMajorColleges;
	}

}
