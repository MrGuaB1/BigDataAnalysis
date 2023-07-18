package com.nd.car.web.entity;

import java.io.Serializable;

public class Status implements Serializable {
    String cyear;
    int newCount;
    int usedCount;
    int certifiedCount;
    int otherCount;

    public Status(String cyear, int newCount, int usedCount, int certifiedCount, int otherCount) {
        this.cyear = cyear;
        this.newCount = newCount;
        this.usedCount = usedCount;
        this.certifiedCount = certifiedCount;
        this.otherCount = otherCount;
    }

    public Status() {
    }

    public String getCyear() {
        return cyear;
    }

    public void setCyear(String cyear) {
        this.cyear = cyear;
    }

    public int getNewCount() {
        return newCount;
    }

    public void setNewCount(int newCount) {
        this.newCount = newCount;
    }

    public int getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(int usedCount) {
        this.usedCount = usedCount;
    }

    public int getCertifiedCount() {
        return certifiedCount;
    }

    public void setCertifiedCount(int certifiedCount) {
        this.certifiedCount = certifiedCount;
    }

    public int getOtherCount() {
        return otherCount;
    }

    public void setOtherCount(int otherCount) {
        this.otherCount = otherCount;
    }

    @Override
    public String toString() {
        return "Status{" +
                "cyear='" + cyear + '\'' +
                ", newCount=" + newCount +
                ", usedCount=" + usedCount +
                ", certifiedCount=" + certifiedCount +
                ", otherCount=" + otherCount +
                '}';
    }
}
