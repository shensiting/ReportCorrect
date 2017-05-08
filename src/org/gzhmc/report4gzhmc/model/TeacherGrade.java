package org.gzhmc.report4gzhmc.model;

import java.util.Date;

public class TeacherGrade {
    private Integer cId;

    private Integer cTeacherId;

    private Integer cGradeId;

    private Date cCreateTime;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getcTeacherId() {
        return cTeacherId;
    }

    public void setcTeacherId(Integer cTeacherId) {
        this.cTeacherId = cTeacherId;
    }

    public Integer getcGradeId() {
        return cGradeId;
    }

    public void setcGradeId(Integer cGradeId) {
        this.cGradeId = cGradeId;
    }

    public Date getcCreateTime() {
        return cCreateTime;
    }

    public void setcCreateTime(Date cCreateTime) {
        this.cCreateTime = cCreateTime;
    }
}