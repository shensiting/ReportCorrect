package org.gzhmc.report4gzhmc.model;

import java.util.Date;

public class GradeExam {
    private Integer cId;

    private Integer cGradeId;

    private Integer cExperimentId;

    private Date cCreateTime;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getcGradeId() {
        return cGradeId;
    }

    public void setcGradeId(Integer cGradeId) {
        this.cGradeId = cGradeId;
    }

    public Integer getcExperimentId() {
        return cExperimentId;
    }

    public void setcExperimentId(Integer cExperimentId) {
        this.cExperimentId = cExperimentId;
    }

    public Date getcCreateTime() {
        return cCreateTime;
    }

    public void setcCreateTime(Date cCreateTime) {
        this.cCreateTime = cCreateTime;
    }
}