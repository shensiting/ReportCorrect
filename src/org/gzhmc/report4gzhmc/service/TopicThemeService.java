package org.gzhmc.report4gzhmc.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.gzhmc.report4gzhmc.model.TopicTheme;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
public interface TopicThemeService {
	
	public int deleteByPrimaryKey(int cId);

	public int insert(TopicTheme record);

	public int getCountAll();

	public int getCountMes(int cLaunchId);

	public int getCountAllByExamId(int cExperimentId);

	public int getCountAllByMes(String mes);

	public int updateByPrimaryKeySelective(TopicTheme record);

	public int updateByPrimaryKey(TopicTheme record);

	public List<TopicTheme> getPageAll(int star, int end);

	public List<TopicTheme> getPageAllByExamId(int cExperimentId, int star, int end);

	public List<TopicTheme> getPageAllByMes(String mes, int star, int end);

	public List<TopicTheme> getAll(int cTopicStatus);

	public List<TopicTheme> getByLaunchId(int cLaunchId);

	public List<TopicTheme> getByLaunchIdAndExamId(int cLaunchId, int cExperimentId);

	public int updateStatus(int status, int cCommentNum, int cId);

	public int subComment(int cId);

	public int addComment(int cId);

	public TopicTheme selectByPrimaryKey(int cId);

	public List<TopicTheme> getByExamId(int cExperimentId);

	public List<TopicTheme> getByExamIdExceptStatus(int cExperimentId);

	public List<TopicTheme> getManageAll();

	public List<TopicTheme> getByUserIdAndExamIdAndDate(int cExperimentId, String cUserName, String beginDate,String endDate);

}
