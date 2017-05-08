package org.gzhmc.report4gzhmc.model;

import java.util.Date;

public class TopicResponse {
    private Long cId;

    private Long cTopicId;

    private Date cCreateTime;

    private Integer cLaunchId;

    private String cContent;

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }

    public Long getcTopicId() {
        return cTopicId;
    }

    public void setcTopicId(Long cTopicId) {
        this.cTopicId = cTopicId;
    }

    public Date getcCreateTime() {
        return cCreateTime;
    }

    public void setcCreateTime(Date cCreateTime) {
        this.cCreateTime = cCreateTime;
    }

    public Integer getcLaunchId() {
        return cLaunchId;
    }

    public void setcLaunchId(Integer cLaunchId) {
        this.cLaunchId = cLaunchId;
    }

    public String getcContent() {
        return cContent;
    }

    public void setcContent(String cContent) {
        this.cContent = cContent == null ? null : cContent.trim();
    }
}