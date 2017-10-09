package org.gzhmc.report4gzhmc.mapper;

import java.util.List;

import org.gzhmc.common.base.BaseMapper;
import org.gzhmc.report4gzhmc.model.Report;

public interface ReportMapper extends BaseMapper<Report> {

	public List<Report> getByStudentId(int cStudentId);

	public int getCountByexperimrntAndstudent(Report report);

	public List<Report> getByexperimrntAndstudentId(Report report);

	public int updateScore(Report report);

}
