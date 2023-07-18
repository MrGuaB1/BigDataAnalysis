package com.nd.car.web.entity;

import java.io.Serializable;

public class ColorGroup implements Serializable {
    private String exteriorColor;
    private String interiorColor;
    private int count;

    public ColorGroup(String exteriorColor, String interiorColor, int count) {
        this.exteriorColor = exteriorColor;
        this.interiorColor = interiorColor;
        this.count = count;
    }

    @Override
    public String toString() {
        return "ColorGroup{" +
                "exteriorColor='" + exteriorColor + '\'' +
                ", interiorColor='" + interiorColor + '\'' +
                ", count=" + count +
                '}';
    }

    public String getExteriorColor() {
        return exteriorColor;
    }

    public void setExteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

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
}
