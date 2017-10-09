package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.CollegeMapper;
import org.gzhmc.report4gzhmc.model.College;
import org.gzhmc.report4gzhmc.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 *         2017年11月8日
 */
@Service
public class CollegeServiceImpl implements CollegeService {

	@Autowired
	CollegeMapper collegeMapper;

	@Override
	public int add(College t) {
		return collegeMapper.add(t);
	}

	@Override
	public int delete(int cId) {
		return collegeMapper.delete(cId);
	}

	@Override
	public int update(College t) {
		return collegeMapper.update(t);
	}

	@Override
	public int updateSelective(College t) {
		return collegeMapper.updateSelective(t);
	}

	@Override
	public College getById(int cId) {
	    College college=collegeMapper.getById(cId);
		return college;
	}

	@Override
	public List<College> getAll() {
		List<College> colleges=collegeMapper.getAll();
		return colleges;
	}

	@Override
	public int getByGradeCollegeId(int cCollegeId) {
		int num=collegeMapper.getByGradeCollegeId(cCollegeId);
		return num;
	}

	@Override
	public int getByTeacherCollegeId(int cCollegeId) {
		int num=collegeMapper.getByTeacherCollegeId(cCollegeId);
		return num;
	}
	
}
