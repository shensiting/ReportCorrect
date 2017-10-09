package org.gzhmc.report4gzhmc.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.gzhmc.report4gzhmc.model.TopicResponseLink;

/**
 * @author stshen
 *
 *         2017年10月30日
 */
public interface TopicResponseLinkMapper {
	public List<TopicResponseLink> getByUserIdAndExamIdAndDate(@Param("cExperimentId") int cExperimentId,
			@Param("cUserName") String cUserName, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

	public List<TopicResponseLink> getByUserId(int cLaunchId);

	public List<TopicResponseLink> getAllByUserId(int cLaunchId);

	public List<TopicResponseLink> getByUserIdAndExamId(int cLaunchId, int cExperimentId);

	public List<TopicResponseLink> getAllByUserIdAndExamId(int cLaunchId, int cExperimentId);

}
