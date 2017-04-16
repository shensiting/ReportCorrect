package org.gzhmc.report4gzhmc.model;
/**
 * 教师表
 * @author stShen
 *
 */
public class Teacher {
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

	public String getcTeacherId() {
		return cTeacherId;
	}

	public void setcTeacherId(String cTeacherId) {
		this.cTeacherId = cTeacherId == null ? null : cTeacherId.trim();
	}

	public int getcCollegeId() {
		return cCollegeId;
	}

	public void setcCollegeId(int cCollegeId) {
		this.cCollegeId = cCollegeId;
	}
}