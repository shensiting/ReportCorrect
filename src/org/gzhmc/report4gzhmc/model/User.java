package org.gzhmc.report4gzhmc.model;

import java.util.Date;
/**
 * 用户表
 * @author stShen
 *
 */
public class User {
	private int cId;
	private String cUserName;
	private String cPassword;
	private int cRole;
	private Date cCreateTime;

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcUserName() {
		return cUserName;
	}

	public void setcUserName(String cUserName) {
		this.cUserName = cUserName == null ? null : cUserName.trim();
	}

	public String getcPassword() {
		return cPassword;
	}

	public void setcPassword(String cPassword) {
		this.cPassword = cPassword == null ? null : cPassword.trim();
	}

	public int getcRole() {
		return cRole;
	}

	public void setcRole(int cRole) {
		this.cRole = cRole;
	}

	public Date getcCreateTime() {
		return cCreateTime;
	}

	public void setcCreateTime(Date cCreateTime) {
		this.cCreateTime = cCreateTime;
	}
}