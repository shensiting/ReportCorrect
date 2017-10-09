package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.TestMapper;
import org.gzhmc.report4gzhmc.model.Test;
import org.gzhmc.report4gzhmc.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class TestServiceImpl implements TestService{

	@Autowired
	TestMapper testMapper;
	
	@Override
	public int add(Test t) {		
		return testMapper.add(t);
	}

	@Override
	public int delete(int cId) {		
		return testMapper.delete(cId);
	}

	@Override
	public int update(Test t) {		
		return testMapper.update(t);
	}

	@Override
	public int updateSelective(Test t) {		
		return testMapper.updateSelective(t);
	}

	@Override
	public Test getById(int cId) {
		Test test=testMapper.getById(cId);
		return test;
	}

	@Override
	public List<Test> getAll() {
		List<Test> tests=testMapper.getAll();
		return tests;
	}

	@Override
	public int updateByPrimaryKey(Test test) {		
		return testMapper.updateByPrimaryKey(test);
	}

	@Override
	public int getByExamTestId(int cTestId) {		
		return testMapper.getByExamTestId(cTestId);
	}

}
