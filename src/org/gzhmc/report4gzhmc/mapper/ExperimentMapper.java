package org.gzhmc.report4gzhmc.mapper;

import java.util.List;

import org.gzhmc.common.base.BaseMapper;
import org.gzhmc.report4gzhmc.model.Experiment;

/**
 * @author stshen
 *
 * 2017年9月23日
 */
public interface ExperimentMapper extends BaseMapper<Experiment> {
	public List<Experiment> getByClassify(int cClassify);
}
