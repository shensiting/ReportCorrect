package org.gzhmc.report4gzhmc.model;

public class Test {
    private Integer cId;

    private String cTestName;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcTestName() {
        return cTestName;
    }

    public void setcTestName(String cTestName) {
        this.cTestName = cTestName == null ? null : cTestName.trim();
    }
}