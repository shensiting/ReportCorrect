package org.gzhmc.report4gzhmc.model;

import java.util.Date;

public class TopicResponse {
	private Long cId;

	private int cTopicId;

	private Date cCreateTime;

	private int cLaunchId;

	private String cContent;

	private int cResponseStatus;

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getcResponseStatus() {
		return cResponseStatus;
	}

	public void setcResponseStatus(int cResponseStatus) {
		this.cResponseStatus = cResponseStatus;
	}

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public int getcTopicId() {
		return cTopicId;
	}

	public void setcTopicId(int cTopicId) {
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
		this.cContent = cContent == null ? null : cContent.trim();
	}
}