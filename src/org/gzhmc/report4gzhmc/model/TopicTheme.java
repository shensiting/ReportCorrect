package org.gzhmc.report4gzhmc.model;

import java.util.Date;

public class TopicTheme {
	private int cId;

	private String cTitle;

	private int cLaunchId;

	private Date cCreateTime;

	private int cExperimentId;

	private int cTopicStatus;

	private String cContent;

	private int cCommentNum;

	public int getcCommentNum() {
		return cCommentNum;
	}

	public void setcCommentNum(int cCommentNum) {
		this.cCommentNum = cCommentNum;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcTitle() {
		return cTitle;
	}

	public void setcTitle(String cTitle) {
		this.cTitle = cTitle == null ? null : cTitle.trim();
	}

	public int getcLaunchId() {
		return cLaunchId;
	}

	public void setcLaunchId(int cLaunchId) {
		this.cLaunchId = cLaunchId;
	}

	public Date getcCreateTime() {
		return cCreateTime;
	}

	public void setcCreateTime(Date cCreateTime) {
		this.cCreateTime = cCreateTime;
	}

	public int getcExperimentId() {
		return cExperimentId;
	}

	public void setcExperimentId(int cExperimentId) {
		this.cExperimentId = cExperimentId;
	}

	public int getcTopicStatus() {
		return cTopicStatus;
	}

	public void setcTopicStatus(int cTopicStatus) {
		this.cTopicStatus = cTopicStatus;
	}

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent == null ? null : cContent.trim();
	}
}