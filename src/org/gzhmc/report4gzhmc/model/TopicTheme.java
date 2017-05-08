package org.gzhmc.report4gzhmc.model;

import java.util.Date;

public class TopicTheme {
    private Integer cId;

    private String cTitle;

    private Integer cLaunchId;

    private Date cCreateTime;

    private String cContent;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcTitle() {
        return cTitle;
    }

    public void setcTitle(String cTitle) {
        this.cTitle = cTitle == null ? null : cTitle.trim();
    }

    public Integer getcLaunchId() {
        return cLaunchId;
    }

    public void setcLaunchId(Integer cLaunchId) {
        this.cLaunchId = cLaunchId;
    }

    public Date getcCreateTime() {
        return cCreateTime;
    }

    public void setcCreateTime(Date cCreateTime) {
        this.cCreateTime = cCreateTime;
    }

    public String getcContent() {
        return cContent;
    }

    public void setcContent(String cContent) {
        this.cContent = cContent == null ? null : cContent.trim();
    }
}