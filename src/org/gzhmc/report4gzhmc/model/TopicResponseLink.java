package org.gzhmc.report4gzhmc.model;

import java.util.Date;

/**
 * @author stshen
 *
 *         2017年10月30日
 */
public class TopicResponseLink {
	private Long cId;

	private Long cTopicId;

	private Date cCreateTime;

	private int cLaunchId;

	private String cContent;

	private int cResponseStatus;

	private TopicTheme topicTheme;

	private User user;

	private Experiment experiment;

	public TopicTheme getTopicTheme() {
		return topicTheme;
	}

	public void setTopicTheme(TopicTheme topicTheme) {
		this.topicTheme = topicTheme;
	}

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public Long getcTopicId() {
		return cTopicId;
	}

	public void setcTopicId(Long cTopicId) {
		this.cTopicId = cTopicId;
	}

	public Date getcCreateTime() {
		return cCreateTime;
	}

	public void setcCreateTime(Date cCreateTime) {
		this.cCreateTime = cCreateTime;
	}

	public int getcLaunchId() {
		return cLaunchId;
	}

	public void setcLaunchId(int cLaunchId) {
		this.cLaunchId = cLaunchId;
	}

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
	}

	public int getcResponseStatus() {
		return cResponseStatus;
	}

	public void setcResponseStatus(int cResponseStatus) {
		this.cResponseStatus = cResponseStatus;
	}

	public Experiment getExperiment() {
		return experiment;
	}

	public void setExperiment(Experiment experiment) {
		this.experiment = experiment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
