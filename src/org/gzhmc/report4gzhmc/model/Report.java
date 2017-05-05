package org.gzhmc.report4gzhmc.model;
/**
 * 实验报告表
 * @author stShen
 */
import java.util.Date;

public class Report {

	private int cId;
	private String cReportNum;
	private int cScoreId;
	private int cStudentId;
	private int cStatus;
	private String cPath;
	private Date cCreateTime;
	private String cPdfPath;
	private String cProcess;
	private String cQRcode;
	private int cTeacherId;
    private int cExperimentTextId;
	public String getcPdfPath() {
		return cPdfPath;
	}

	public void setcPdfPath(String cPdfPath) {
		this.cPdfPath = cPdfPath;
	}

	public String getcProcess() {
		return cProcess;
	}

	public void setcProcess(String cProcess) {
		this.cProcess = cProcess;
	}

	public String getcQRcode() {
		return cQRcode;
	}

	public void setcQRcode(String cQRcode) {
		this.cQRcode = cQRcode;
	}
	public int getcScoreId() {
		return cScoreId;
	}

	public void setcScoreId(int cScoreId) {
		this.cScoreId = cScoreId;
	}

	public int getcStudentId() {
		return cStudentId;
	}

	public void setcStudentId(int cStudentId) {
		this.cStudentId = cStudentId;
	}

	public int getcStatus() {
		return cStatus;
	}

	public void setcStatus(int cStatus) {
		this.cStatus = cStatus;
	}

	public String getcPath() {
		return cPath;
	}

	public void setcPath(String cPath) {
		this.cPath = cPath;
	}

	public Date getcCreateTime() {
		return cCreateTime;
	}

	public void setcCreateTime(Date cCreateTime) {
		this.cCreateTime = cCreateTime;
	}

	public int getcTeacherId() {
		return cTeacherId;
	}

	public void setcTeacherId(int cTeacherId) {
		this.cTeacherId = cTeacherId;
	}

	public int getcExperimentTextId() {
		return cExperimentTextId;
	}

	public void setcExperimentTextId(int cExperimentTextId) {
		this.cExperimentTextId = cExperimentTextId;
	}

	public String getcReportNum() {
		return cReportNum;
	}

	public void setcReportNum(String cReportNum) {
		this.cReportNum = cReportNum;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

}
