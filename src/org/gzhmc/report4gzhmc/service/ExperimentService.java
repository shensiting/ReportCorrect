package org.gzhmc.report4gzhmc.service;

import java.util.List;

import org.gzhmc.common.base.BaseService;
import org.gzhmc.report4gzhmc.model.Experiment;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
public interface ExperimentService extends BaseService<Experiment>{
	public List<Experiment> getByClassify(int cClassify);
}
