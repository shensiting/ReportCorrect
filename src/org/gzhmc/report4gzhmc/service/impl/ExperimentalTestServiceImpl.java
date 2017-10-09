package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.ExperimentalTestMapper;
import org.gzhmc.report4gzhmc.model.ExperimentalTest;
import org.gzhmc.report4gzhmc.service.ExperimentalTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class ExperimentalTestServiceImpl implements ExperimentalTestService{

	@Autowired
	ExperimentalTestMapper experimentalTestMapper;
	
	@Override
	public int add(ExperimentalTest t) {		
		return experimentalTestMapper.add(t);
	}

	@Override
	public int delete(int cId) {		
		return experimentalTestMapper.delete(cId);
	}

	@Override
	public int update(ExperimentalTest t) {		
		return experimentalTestMapper.update(t);
	}

	@Override
	public int updateSelective(ExperimentalTest t) {		
		return experimentalTestMapper.updateSelective(t);
	}

	@Override
	public ExperimentalTest getById(int cId) {		
		return experimentalTestMapper.getById(cId);
	}

	@Override
	public List<ExperimentalTest> getAll() {
		List<ExperimentalTest> experimentalTests=experimentalTestMapper.getAll();
		return experimentalTests;
	}

}
