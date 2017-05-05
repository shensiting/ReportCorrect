package org.gzhmc.report4gzhmc.model;
/**
 * 教师实验关联表
 * @author stShen
 *
 */
public class TeacherExperimental {
	private int cId;
	private int cTeacherId;
	private int cExperimentalTestId;

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

	public int getcExperimentalTestId() {
		return cExperimentalTestId;
	}

	public void setcExperimentalTestId(int cExperimentalTestId) {
		this.cExperimentalTestId = cExperimentalTestId;
	}
}
