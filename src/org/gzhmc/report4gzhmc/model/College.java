package org.gzhmc.report4gzhmc.model;
/**
 * 学院表
 * @author Administrator
 *
 */
public class College {
	private int cId;
	private String cCollegeName;

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcCollegeName() {
		return cCollegeName;
	}

	public void setcCollegeName(String cCollegeName) {
		this.cCollegeName = cCollegeName == null ? null : cCollegeName.trim();
	}
}