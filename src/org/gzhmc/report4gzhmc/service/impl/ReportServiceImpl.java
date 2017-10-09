package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.ReportMapper;
import org.gzhmc.report4gzhmc.model.Grade;
import org.gzhmc.report4gzhmc.model.Report;
import org.gzhmc.report4gzhmc.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	ReportMapper reportMapper;
	
	@Override
	public int add(Report t) {		
		return reportMapper.add(t);
	}

	@Override
	public int delete(int cId) {		
		return reportMapper.delete(cId);
	}

	@Override
	public int update(Report t) {		
		return reportMapper.update(t);
	}

	@Override
	public int updateSelective(Report t) {		
		return reportMapper.updateSelective(t);
	}

	@Override
	public Report getById(int cId) {	
		return reportMapper.getById(cId);
	}

	@Override
	public List<Report> getAll() {
		List<Report> reports=reportMapper.getAll();
		return reports;
	}

	@Override
	public List<Report> getByStudentId(int cStudentId) {
		List<Report> reports=reportMapper.getByStudentId(cStudentId);
		return reports;
	}

	@Override
	public int getCountByexperimrntAndstudent(Report report) {		
		return reportMapper.getCountByexperimrntAndstudent(report);
	}

	@Override
	public List<Report> getByexperimrntAndstudentId(Report report) {
		List<Report> reports=reportMapper.getByexperimrntAndstudentId(report);
		return reports;
	}

	@Override
	public int updateScore(Report report) {		
		return reportMapper.updateScore(report);
	}

}
