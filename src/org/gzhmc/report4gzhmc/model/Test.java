package org.gzhmc.report4gzhmc.model;

public class Test {
    private int cId;

    private String cTestName;

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcTestName() {
        return cTestName;
    }

    public void setcTestName(String cTestName) {
        this.cTestName = cTestName == null ? null : cTestName.trim();
    }
}