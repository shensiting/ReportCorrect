package org.gzhmc.report4gzhmc.model;

public class TeacherlinkExperiment {
	private int cId;
	private int cTeacherId;
	private int cExperimentalTestId;
	private Teacher teacher;
	private ExperimentalTest experimentalTest;

	public int getcExperimentalTestId() {
		return cExperimentalTestId;
	}

	public void setcExperimentalTestId(int cExperimentalTestId) {
		this.cExperimentalTestId = cExperimentalTestId;
	}

	public ExperimentalTest getExperimentalTest() {
		return experimentalTest;
	}

	public void setExperimentalTest(ExperimentalTest experimentalTest) {
		this.experimentalTest = experimentalTest;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public int getcTeacherId() {
		return cTeacherId;
	}

	public void setcTeacherId(int cTeacherId) {
		this.cTeacherId = cTeacherId;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}
