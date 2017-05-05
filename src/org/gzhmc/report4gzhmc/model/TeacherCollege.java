package org.gzhmc.report4gzhmc.model;

public class TeacherCollege {
	private int cUserId;
	private String cName;
	private String cTeacherId;
	private int cCollegeId;
	private int cVerify;

	public int getcVerify() {
		return cVerify;
	}

	public void setcVerify(int cVerify) {
		this.cVerify = cVerify;
	}

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

	public String getcTeacherId() {
		return cTeacherId;
	}

	public void setcTeacherId(String cTeacherId) {
		this.cTeacherId = cTeacherId;
	}

	public int getcCollegeId() {
		return cCollegeId;
	}

	public void setcCollegeId(int cCollegeId) {
		this.cCollegeId = cCollegeId;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}
}
