package org.gzhmc.report4gzhmc.model;

import java.util.Date;

public class GradeExam {
	private int cId;
	private int cGradeId;
	private int cExperimentId;
	private int cStatus;
	private Date cCreateTime;
	private int cSubmitForm;
	private Grade grade;
	private Major major;
	private College college;
	private ExperimentalTest experiment;

	public int getcSubmitForm() {
		return cSubmitForm;
	}

	public void setcSubmitForm(int cSubmitForm) {
		this.cSubmitForm = cSubmitForm;
	}

	public int getcStatus() {
		return cStatus;
	}

	public void setcStatus(int cStatus) {
		this.cStatus = cStatus;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public ExperimentalTest getExperiment() {
		return experiment;
	}

	public void setExperiment(ExperimentalTest experiment) {
		this.experiment = experiment;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public int getcGradeId() {
		return cGradeId;
	}

	public void setcGradeId(int cGradeId) {
		this.cGradeId = cGradeId;
	}

	public int getcExperimentId() {
		return cExperimentId;
	}

	public void setcExperimentId(int cExperimentId) {
		this.cExperimentId = cExperimentId;
	}

	public Date getcCreateTime() {
		return cCreateTime;
	}

	public void setcCreateTime(Date cCreateTime) {
		this.cCreateTime = cCreateTime;
	}
}