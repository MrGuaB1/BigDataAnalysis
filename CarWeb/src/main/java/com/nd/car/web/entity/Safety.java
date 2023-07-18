package com.nd.car.web.entity;

import java.io.Serializable;

public class Safety implements Serializable {
    private String safety;
    private int count;

    public Safety(String safety, int count) {
        this.safety = safety;
        this.count = count;
    }

    public Safety() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSafety() {
        return safety;
    }

    public void setSafety(String safety) {
        this.safety = safety;
    }

    @Override
    public String toString() {
        return "Safety{" +
                "safety='" + safety + '\'' +
                ", count=" + count +
                '}';
    }
}
