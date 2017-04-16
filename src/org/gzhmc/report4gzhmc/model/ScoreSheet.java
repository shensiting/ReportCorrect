package org.gzhmc.report4gzhmc.model;

import java.util.Date;
/**
 * 成绩表
 * @author stShen
 *
 */
public class ScoreSheet {
	private int cId;
	private Double cTherory;
	private Double cReagen;
	private Double cInserument;
	private Double cExperiment;
	private Double cLabresult;
	private Double cSum;
	private String cComment;
	private Date cCreateTime;
	private Double cConclution;

	public Double getcConclution() {
		return cConclution;
	}

	public void setcConclution(Double cConclution) {
		this.cConclution = cConclution;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public Double getcTherory() {
		return cTherory;
	}

	public void setcTherory(Double cTherory) {
		this.cTherory = cTherory;
	}

	public Double getcReagen() {
		return cReagen;
	}

	public void setcReagen(Double cReagen) {
		this.cReagen = cReagen;
	}

	public Double getcInserument() {
		return cInserument;
	}

	public void setcInserument(Double cInserument) {
		this.cInserument = cInserument;
	}

	public Double getcExperiment() {
		return cExperiment;
	}

	public void setcExperiment(Double cExperiment) {
		this.cExperiment = cExperiment;
	}

	public Double getcLabresult() {
		return cLabresult;
	}

	public void setcLabresult(Double cLabresult) {
		this.cLabresult = cLabresult;
	}

	public Double getcSum() {
		return cSum;
	}

	public void setcSum(Double cSum) {
		this.cSum = cSum;
	}

	public String getcComment() {
		return cComment;
	}

	public void setcComment(String cComment) {
		this.cComment = cComment == null ? null : cComment.trim();
	}

	public Date getcCreateTime() {
		return cCreateTime;
	}

	public void setcCreateTime(Date cCreateTime) {
		this.cCreateTime = cCreateTime;
	}
}