package org.gzhmc.report4gzhmc.model;

import java.util.Date;

/**
 * 教师与班级表格
 * 
 * @author stshen
 *
 *         2017年9月25日
 */
public class TeacherGrade {
	private int cId;

	private int cTeacherId;

	private int cGradeId;

	private Date cCreateTime;
	private int cStatus;

	public int getcStatus() {
		return cStatus;
	}

	public void setcStatus(int cStatus) {
		this.cStatus = cStatus;
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

	public int getcGradeId() {
		return cGradeId;
	}

	public void setcGradeId(int cGradeId) {
		this.cGradeId = cGradeId;
	}

	public Date getcCreateTime() {
		return cCreateTime;
	}

	public void setcCreateTime(Date cCreateTime) {
		this.cCreateTime = cCreateTime;
	}
}