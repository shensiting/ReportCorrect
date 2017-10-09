package org.gzhmc.report4gzhmc.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.gzhmc.report4gzhmc.mapper.TopicThemeMapper;
import org.gzhmc.report4gzhmc.model.TopicResponseLink;
import org.gzhmc.report4gzhmc.model.TopicTheme;
import org.gzhmc.report4gzhmc.service.TopicThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class TopicThemeServiceImpl implements TopicThemeService {

	@Autowired
	TopicThemeMapper topicThemeMapper;
	
	
	@Override
	public int deleteByPrimaryKey(int cId) {		
		return topicThemeMapper.deleteByPrimaryKey(cId);
	}

	@Override
	public int insert(TopicTheme record) {		
		return topicThemeMapper.insert(record);
	}

	@Override
	public int getCountAll() {		
		return topicThemeMapper.getCountAll();
	}

	@Override
	public int getCountMes(int cLaunchId) {		
		return topicThemeMapper.getCountMes(cLaunchId);
	}

	@Override
	public int getCountAllByExamId(int cExperimentId) {		
		return topicThemeMapper.getCountAllByExamId(cExperimentId);
	}

	@Override
	public int getCountAllByMes(String mes) {		
		return topicThemeMapper.getCountAllByMes(mes);
	}

	@Override
	public int updateByPrimaryKeySelective(TopicTheme record) {		
		return topicThemeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TopicTheme record) {		
		return topicThemeMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TopicTheme> getPageAll(int star, int end) {
		List<TopicTheme> topicThemes=topicThemeMapper.getPageAll(star, end);
		return topicThemes;
	}

	@Override
	public List<TopicTheme> getPageAllByExamId(int cExperimentId, int star, int end) {
		List<TopicTheme> topicThemes=topicThemeMapper.getPageAllByExamId(cExperimentId,star, end);
		return topicThemes;
	}

	@Override
	public List<TopicTheme> getPageAllByMes(String mes, int star, int end) {
		List<TopicTheme> topicThemes=topicThemeMapper.getPageAllByMes(mes,star, end);
		return topicThemes;
	}

	@Override
	public List<TopicTheme> getAll(int cTopicStatus) {
		List<TopicTheme> topicThemes=topicThemeMapper.getAll(cTopicStatus);
		return topicThemes;
	}

	@Override
	public List<TopicTheme> getByLaunchId(int cLaunchId) {
		List<TopicTheme> topicThemes=topicThemeMapper.getByLaunchId(cLaunchId);
		return topicThemes;
	}

	@Override
	public List<TopicTheme> getByLaunchIdAndExamId(int cLaunchId, int cExperimentId) {
		List<TopicTheme> topicThemes=topicThemeMapper.getByLaunchIdAndExamId(cLaunchId,cExperimentId);
		return topicThemes;
	}

	@Override
	public int updateStatus(int status, int cCommentNum, int cId) {		
		return topicThemeMapper.updateStatus(status, cCommentNum, cId);
	}

	@Override
	public int subComment(int cId) {		
		return topicThemeMapper.subComment(cId);
	}

	@Override
	public int addComment(int cId) {		
		return topicThemeMapper.addComment(cId);
	}

	@Override
	public TopicTheme selectByPrimaryKey(int cId) {		
		return topicThemeMapper.selectByPrimaryKey(cId);
	}

	@Override
	public List<TopicTheme> getByExamId(int cExperimentId) {
		List<TopicTheme> topicThemes=topicThemeMapper.getByExamId(cExperimentId);
		return topicThemes;
	}

	@Override
	public List<TopicTheme> getByExamIdExceptStatus(int cExperimentId) {
		List<TopicTheme> topicThemes=topicThemeMapper.getByExamIdExceptStatus(cExperimentId);
		return topicThemes;
	}

	@Override
	public List<TopicTheme> getManageAll() {
		List<TopicTheme> topicThemes=topicThemeMapper.getManageAll();
		return topicThemes;
	}

	@Override
	public List<TopicTheme> getByUserIdAndExamIdAndDate(int cExperimentId, String cUserName, String beginDate,String endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date starDate = null, finishDate = null;		
		try {
			if (null != beginDate && !beginDate.equals("")) {
				starDate = sdf.parse(beginDate);
			}
			if (null != endDate && !endDate.equals("")) {
				finishDate = sdf.parse(endDate);
			}			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TopicTheme> topicThemes = topicThemeMapper.getByUserIdAndExamIdAndDate(cExperimentId,cUserName, starDate, finishDate);	
		return topicThemes;
	}
	

}
