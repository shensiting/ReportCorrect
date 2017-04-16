package org.gzhmc.report4gzhmc.model;

import java.util.Date;

public class ReportRelative {
	private String cId;
	private String cReportNum;
	private int cScoreId;
	private int cStudentId;
	private int cTeacherId;
	private int cStatus;
	private String cPath;
	private Date cCreateTime;
	private String cPdfPath;
	private String cProcess;
	private String cQRcode;
    private int cExperimentTextId;
	private ScoreSheet scoreSheet;
	private ExperimentalTest experimentalTest;
	private Student student;

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
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

	public ScoreSheet getScoreSheet() {
		return scoreSheet;
	}

	public void setScoreSheet(ScoreSheet scoreSheet) {
		this.scoreSheet = scoreSheet;
	}

	public ExperimentalTest getExperimentalTest() {
		return experimentalTest;
	}

	public void setExperimentalTest(ExperimentalTest experimentalTest) {
		this.experimentalTest = experimentalTest;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getcTeacherId() {
		return cTeacherId;
	}

	public void setcTeacherId(int cTeacherId) {
		this.cTeacherId = cTeacherId;
	}

	public String getcReportNum() {
		return cReportNum;
	}

	public void setcReportNum(String cReportNum) {
		this.cReportNum = cReportNum;
	}

	public int getcExperimentTextId() {
		return cExperimentTextId;
	}

	public void setcExperimentTextId(int cExperimentTextId) {
		this.cExperimentTextId = cExperimentTextId;
	}

}
