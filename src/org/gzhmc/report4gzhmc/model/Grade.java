package org.gzhmc.report4gzhmc.model;
/**
 * 班级表
 * @author stShen
 *
 */
public class Grade {
	private int cId;
	private String cYearClass;
	private int cMajorId;
	private String cClass;
	private int cCollegeId;

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
		this.cYearClass = cYearClass == null ? null : cYearClass.trim();
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
		this.cClass = cClass == null ? null : cClass.trim();
	}

	public int getcCollegeId() {
		return cCollegeId;
	}

	public void setcCollegeId(int cCollegeId) {
		this.cCollegeId = cCollegeId;
	}
}