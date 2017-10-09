package org.gzhmc.report4gzhmc.model;

/**
 * 学生表
 * 
 * @author stShen
 *
 */
public class Student {
	private int cUserId;
	private String cName;
	private String cStudentNumber;
	private int cGradeId;
	private String cPicturePath;
	private String cPhoneNum;

	public String getcPhoneNum() {
		return cPhoneNum;
	}

	public void setcPhoneNum(String cPhoneNum) {
		this.cPhoneNum = cPhoneNum;
	}

	public void setcGradeId(int cGradeId) {
		this.cGradeId = cGradeId;
	}

	public String getcPicturePath() {
		return cPicturePath;
	}

	public void setcPicturePath(String cPicturePath) {
		this.cPicturePath = cPicturePath;
	}

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
		this.cName = cName == null ? null : cName.trim();
	}

	public String getcStudentNumber() {
		return cStudentNumber;
	}

	public void setcStudentNumber(String cStudentNumber) {
		this.cStudentNumber = cStudentNumber == null ? null : cStudentNumber.trim();
	}

	public int getcGradeId() {
		return cGradeId;
	}

}