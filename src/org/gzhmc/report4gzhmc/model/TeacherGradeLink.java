package org.gzhmc.report4gzhmc.model;

import java.util.Date;

/**
 * 教师与班级关联查询
 * 
 * @author stshen
 *
 *         2017年9月25日
 */
public class TeacherGradeLink {
	private int cId;
	private int cTeacherId;
	private int cGradeId;
	private Date cCreateTime;
	private int cStatus;
	private Teacher teacher;
	private Grade grade;
	private Major major;
	private College college;

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

	public int getcStatus() {
		return cStatus;
	}

	public void setcStatus(int cStatus) {
		this.cStatus = cStatus;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}
