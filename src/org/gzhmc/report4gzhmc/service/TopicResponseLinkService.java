package org.gzhmc.report4gzhmc.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.gzhmc.report4gzhmc.model.TopicResponseLink;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
public interface TopicResponseLinkService {
	
	public List<TopicResponseLink> getByUserIdAndExamIdAndDate(int cExperimentId, String cUserName, String beginDate, String endDate);

	public List<TopicResponseLink> getByUserId(int cLaunchId);

	public List<TopicResponseLink> getAllByUserId(int cLaunchId);

	/**
	 * 根据发表人id与实验id获取回复部分内容
	 * @param cLaunchId
	 * @param cExperimentId
	 * @return
	 */
	public List<TopicResponseLink> getByUserIdAndExamId(int cLaunchId, int cExperimentId);

	/**
	 * 根据发表人id与实验id获取回复全部内容
	 * @param cLaunchId
	 * @param cExperimentId
	 * @return
	 */
	public List<TopicResponseLink> getAllByUserIdAndExamId(int cLaunchId, int cExperimentId);

}
