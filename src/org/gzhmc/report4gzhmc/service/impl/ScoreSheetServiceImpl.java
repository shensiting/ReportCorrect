package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.ScoreSheetMapper;
import org.gzhmc.report4gzhmc.model.ScoreSheet;
import org.gzhmc.report4gzhmc.service.ScoreSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class ScoreSheetServiceImpl implements ScoreSheetService{

	@Autowired
	ScoreSheetMapper scoreSheetMapper;
	
	@Override
	public int add(ScoreSheet t) {
		return scoreSheetMapper.add(t);
	}

	@Override
	public int delete(int cId) {
		return scoreSheetMapper.delete(cId);
	}

	@Override
	public int update(ScoreSheet t) {
		return scoreSheetMapper.update(t);
	}

	@Override
	public int updateSelective(ScoreSheet t) {
		return scoreSheetMapper.updateSelective(t);
	}

	@Override
	public ScoreSheet getById(int cId) {
	    ScoreSheet scoreSheet=scoreSheetMapper.getById(cId);
		return scoreSheet;
	}

	@Override
	public List<ScoreSheet> getAll() {
		List<ScoreSheet> scoreSheets=scoreSheetMapper.getAll();
		return scoreSheets;
	}


}
