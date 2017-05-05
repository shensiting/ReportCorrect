package org.gzhmc.report4gzhmc.model;

public class StudentGrade {
	private int cUserId;
	private String cName;
	private String cStudentNumber;
	private int cGradeId;
	private String cIDNumber;
	private String cPicturePath;
	private Grade grade;
	private Major major;
	private College college;

	public int getcUserId() {
		return cUserId;
	}

	public void setcUserId(int cUserId) {
		this.cUserId = cUserId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcStudentNumber() {
		return cStudentNumber;
	}

	public void setcStudentNumber(String cStudentNumber) {
		this.cStudentNumber = cStudentNumber;
	}

	public int getcGradeId() {
		return cGradeId;
	}

	public void setcGradeId(int cGradeId) {
		this.cGradeId = cGradeId;
	}

	public String getcIDNumber() {
		return cIDNumber;
	}

	public void setcIDNumber(String cIDNumber) {
		this.cIDNumber = cIDNumber;
	}

	public String getcPicturePath() {
		return cPicturePath;
	}

	public void setcPicturePath(String cPicturePath) {
		this.cPicturePath = cPicturePath;
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

}
