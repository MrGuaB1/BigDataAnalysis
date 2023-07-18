package com.nd.car.web.entity;

import java.io.Serializable;

public class InteriorColor implements Serializable {
    String interiorColor;
    int count;

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public InteriorColor(String interiorColor, int count) {
        this.interiorColor = interiorColor;
        this.count = count;
    }

    public InteriorColor() {
    }

    @Override
    public String toString() {
        return "InteriorColor{" +
                "interiorColor='" + interiorColor + '\'' +
                ", count=" + count +
                '}';
    }
}
