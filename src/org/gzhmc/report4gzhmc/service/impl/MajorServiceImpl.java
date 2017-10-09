package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.MajorMapper;
import org.gzhmc.report4gzhmc.model.Grade;
import org.gzhmc.report4gzhmc.model.Major;
import org.gzhmc.report4gzhmc.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class MajorServiceImpl implements MajorService{

	@Autowired
	MajorMapper majorMapper;
	
	@Override
	public int add(Major t) {		
		return majorMapper.add(t);
	}

	@Override
	public int delete(int cId) {		
		return majorMapper.delete(cId);
	}

	@Override
	public int update(Major t) {		
		return majorMapper.update(t);
	}

	@Override
	public int updateSelective(Major t) {		
		return majorMapper.updateSelective(t);
	}

	@Override
	public Major getById(int cId) {	
		return majorMapper.getById(cId);
	}

	@Override
	public List<Major> getAll() {
		List<Major> majors=majorMapper.getAll();
		return majors;
	}

	@Override
	public int getByGradeMajorId(int cMajorId) {		
		return majorMapper.getByGradeMajorId(cMajorId);
	}

}
