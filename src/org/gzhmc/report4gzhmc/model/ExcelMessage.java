package org.gzhmc.report4gzhmc.model;
/**
 * 导出表格所用信息
 * @author stShen
 *
 */

public class ExcelMessage {

	private String cName;
	private String cStudentNumber;
	private Double cSum;
	private String cReportNum;
	private String cMajorName;
	private String cYearClass;
	private String cClass;
	private String cCollegeName;
	private String cExperimentName;

	public String getcMajorName() {
		return cMajorName;
	}

	public void setcMajorName(String cMajorName) {
		this.cMajorName = cMajorName;
	}

	public String getcYearClass() {
		return cYearClass;
	}

	public void setcYearClass(String cYearClass) {
		this.cYearClass = cYearClass;
	}

	public String getcClass() {
		return cClass;
	}

	public void setcClass(String cClass) {
		this.cClass = cClass;
	}

	public String getcCollegeName() {
		return cCollegeName;
	}

	public void setcCollegeName(String cCollegeName) {
		this.cCollegeName = cCollegeName;
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

	public Double getcSum() {
		return cSum;
	}

	public void setcSum(Double cSum) {
		this.cSum = cSum;
	}

	public String getcReportNum() {
		return cReportNum;
	}

	public void setcReportNum(String cReportNum) {
		this.cReportNum = cReportNum;
	}

	public String getcExperimentName() {
		return cExperimentName;
	}

	public void setcExperimentName(String cExperimentName) {
		this.cExperimentName = cExperimentName;
	}

}
