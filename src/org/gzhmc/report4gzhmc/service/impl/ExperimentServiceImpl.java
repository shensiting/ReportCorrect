package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.ExperimentMapper;
import org.gzhmc.report4gzhmc.model.Experiment;
import org.gzhmc.report4gzhmc.service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class ExperimentServiceImpl implements ExperimentService{

	@Autowired
	ExperimentMapper experimentMapper;
	
	@Override
	public int add(Experiment t) {		
		return experimentMapper.add(t);
	}

	@Override
	public int delete(int cId) {		
		return experimentMapper.delete(cId);
	}

	@Override
	public int update(Experiment t) {		
		return experimentMapper.update(t);
	}

	@Override
	public int updateSelective(Experiment t) {		
		return experimentMapper.updateSelective(t);
	}

	@Override
	public Experiment getById(int cId) {		
		return experimentMapper.getById(cId);
	}

	@Override
	public List<Experiment> getAll() {
		List<Experiment> experiments=experimentMapper.getAll();
		return experiments;
	}

	@Override
	public List<Experiment> getByClassify(int cClassify) {
		List<Experiment> experiments=experimentMapper.getByClassify(cClassify);
		return experiments;
	}

}
