package com.nd.car.web.entity;

import java.io.Serializable;

public class Fuel implements Serializable {
    private int cyear;
    private String fuelType;
    private int count;

    public int getCyear() {
        return cyear;
    }

    public void setCyear(int cyear) {
        this.cyear = cyear;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Fuel(int year, String fuelType, int count) {
        this.cyear = year;
        this.fuelType = fuelType;
        this.count = count;
    }

    public Fuel() {
    }

    @Override
    public String toString() {
        return "Fuel{" +
                "cyear=" + cyear +
                ", fuelType='" + fuelType + '\'' +
                ", count=" + count +
                '}';
    }
}
