package org.gzhmc.report4gzhmc.model;

/**
 * 
 * @author stShen
 * @date 2017年2月5日
 */
public class Resit {
	private int cId;

	private int cStudentId;

	private int cExperiment;

	private int cReportId;

	public int getcId() {
		return cId;
	}

	public int getcStudentId() {
		return cStudentId;
	}

	public void setcStudentId(int cStudentId) {
		this.cStudentId = cStudentId;
	}

	public int getcExperiment() {
		return cExperiment;
	}

	public void setcExperiment(int cExperiment) {
		this.cExperiment = cExperiment;
	}

	public int getcReportId() {
		return cReportId;
	}

	public void setcReportId(int cReportId) {
		this.cReportId = cReportId;
	}
}