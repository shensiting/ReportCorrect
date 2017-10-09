package org.gzhmc.report4gzhmc.service;

import java.util.List;

import org.gzhmc.common.base.BaseService;
import org.gzhmc.report4gzhmc.model.Report;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
public interface ReportService extends BaseService<Report>{
	public List<Report> getByStudentId(int cStudentId);

	public int getCountByexperimrntAndstudent(Report report);

	public List<Report> getByexperimrntAndstudentId(Report report);

	public int updateScore(Report report);
}
