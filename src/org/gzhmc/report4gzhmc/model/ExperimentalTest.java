package org.gzhmc.report4gzhmc.model;

/**
 * 实验表
 * 
 * @author stShen
 *
 */

public class ExperimentalTest {
	private int cId;

	private String cExperimentName;

	private String cExperimentTime;

	private String cExperimentEnglishName;

	private int cClassify;

	private Test test;

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public int getcClassify() {
		return cClassify;
	}

	public void setcClassify(int cClassify) {
		this.cClassify = cClassify;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcExperimentName() {
		return cExperimentName;
	}

	public void setcExperimentName(String cExperimentName) {
		this.cExperimentName = cExperimentName == null ? null : cExperimentName.trim();
	}

	public String getcExperimentTime() {
		return cExperimentTime;
	}

	public void setcExperimentTime(String cExperimentTime) {
		this.cExperimentTime = cExperimentTime;
	}

	public String getcExperimentEnglishName() {
		return cExperimentEnglishName;
	}

	public void setcExperimentEnglishName(String cExperimentEnglishName) {
		this.cExperimentEnglishName = cExperimentEnglishName == null ? null : cExperimentEnglishName.trim();
	}

}