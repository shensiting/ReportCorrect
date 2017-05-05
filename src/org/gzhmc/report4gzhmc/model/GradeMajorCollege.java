package org.gzhmc.report4gzhmc.model;

/**
 * 学院年级班级信息
 * @author stShen
 */
public class GradeMajorCollege {

	private int cId;
	private String cYearClass;
	private int cMajorId;
	private String cClass;
	private int cCollegeId;
	private Major major;
	private College college;

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcYearClass() {
		return cYearClass;
	}

	public void setcYearClass(String cYearClass) {
		this.cYearClass = cYearClass;
	}

	public int getcMajorId() {
		return cMajorId;
	}

	public void setcMajorId(int cMajorId) {
		this.cMajorId = cMajorId;
	}

	public String getcClass() {
		return cClass;
	}

	public void setcClass(String cClass) {
		this.cClass = cClass;
	}

	public int getcCollegeId() {
		return cCollegeId;
	}

	public void setcCollegeId(int cCollegeId) {
		this.cCollegeId = cCollegeId;
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

}
