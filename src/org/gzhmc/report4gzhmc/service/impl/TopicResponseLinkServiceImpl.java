package org.gzhmc.report4gzhmc.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.gzhmc.report4gzhmc.mapper.TopicResponseLinkMapper;
import org.gzhmc.report4gzhmc.model.TopicResponseLink;
import org.gzhmc.report4gzhmc.service.TopicResponseLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class TopicResponseLinkServiceImpl implements TopicResponseLinkService {

	@Autowired
	TopicResponseLinkMapper topicResponseLinkMapper;
	
	@Override
	public List<TopicResponseLink> getByUserIdAndExamIdAndDate(int cExperimentId, String cUserName, String beginDate,
			String endDate) {
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
		List<TopicResponseLink> topicResponseLinks = topicResponseLinkMapper.getByUserIdAndExamIdAndDate(cExperimentId,cUserName, starDate, finishDate);	
		return topicResponseLinks;
	}

	@Override
	public List<TopicResponseLink> getByUserId(int cLaunchId) {
		List<TopicResponseLink> topicResponseLinks=topicResponseLinkMapper.getByUserId(cLaunchId);
		return topicResponseLinks;
	}

	@Override
	public List<TopicResponseLink> getAllByUserId(int cLaunchId) {
		List<TopicResponseLink> topicResponseLinks=topicResponseLinkMapper.getAllByUserId(cLaunchId);
		return topicResponseLinks;
	}

	@Override
	public List<TopicResponseLink> getByUserIdAndExamId(int cLaunchId, int cExperimentId) {
		List<TopicResponseLink> topicResponseLinks=topicResponseLinkMapper.getByUserIdAndExamId(cLaunchId, cExperimentId);
		return topicResponseLinks;
	}

	@Override
	public List<TopicResponseLink> getAllByUserIdAndExamId(int cLaunchId, int cExperimentId) {
		List<TopicResponseLink> topicResponseLinks=topicResponseLinkMapper.getAllByUserIdAndExamId(cLaunchId, cExperimentId);
		return topicResponseLinks;
	}

}
