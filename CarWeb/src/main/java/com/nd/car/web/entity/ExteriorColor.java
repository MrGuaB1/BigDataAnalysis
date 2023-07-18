package com.nd.car.web.entity;

import java.io.Serializable;

public class ExteriorColor implements Serializable {
    String exteriorColor;
    int count;

    public String getExteriorColor() {
        return exteriorColor;
    }

    public void setExteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ExteriorColor(String exteriorColor, int count) {
        this.exteriorColor = exteriorColor;
        this.count = count;
    }

    public ExteriorColor() {
    }

    @Override
    public String toString() {
        return "ExteriorColor{" +
                "exteriorColor='" + exteriorColor + '\'' +
                ", count=" + count +
                '}';
    }
}
