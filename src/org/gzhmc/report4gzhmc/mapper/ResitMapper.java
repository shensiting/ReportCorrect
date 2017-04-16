package org.gzhmc.report4gzhmc.mapper;

import java.util.List;

import org.gzhmc.report4gzhmc.model.Resit;

public interface ResitMapper {
	public int deleteByPrimaryKey(int cId);

	public int insert(Resit record);

	public int insertSelective(Resit record);

	public List<Resit> getAll();

	public int updateByPrimaryKeySelective(Resit record);

	public int updateByPrimaryKey(Resit record);
	
	public int  updateByStudentIdAndExperimentId(Resit record);
}