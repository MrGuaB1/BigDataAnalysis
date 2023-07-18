package com.nd.car.web.entity;

import java.io.Serializable;

public class PopularBrand implements Serializable {
    private int min;
    private int max;
    private String brand;
    private String drivetrain;
    private String MPG;
    private String fuelType;
    private String cengine;
    private String convenience;
    private String entertainment;
    private String model;

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDrivetrain() {
        return drivetrain;
    }

    public void setDrivetrain(String drivetrain) {
        this.drivetrain = drivetrain;
    }

    public String getMPG() {
        return MPG;
    }

    public void setMPG(String MPG) {
        this.MPG = MPG;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getCengine() {
        return cengine;
    }

    public void setCengine(String cengine) {
        this.cengine = cengine;
    }

    public String getConvenience() {
        return convenience;
    }

    public void setConvenience(String convenience) {
        this.convenience = convenience;
    }

    public String getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(String entertainment) {
        this.entertainment = entertainment;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public PopularBrand(int min, int max, String brand, String drivetrain, String MPG, String fuelType, String cengine, String convenience, String entertainment, String model) {
        this.min = min;
        this.max = max;
        this.brand = brand;
        this.drivetrain = drivetrain;
        this.MPG = MPG;
        this.fuelType = fuelType;
        this.cengine = cengine;
        this.convenience = convenience;
        this.entertainment = entertainment;
        this.model = model;
    }

    public PopularBrand() {
    }

    @Override
    public String toString() {
        return "PopularBrand{" +
                "min=" + min +
                ", max=" + max +
                ", brand='" + brand + '\'' +
                ", drivetrain='" + drivetrain + '\'' +
                ", MPG='" + MPG + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", cengine='" + cengine + '\'' +
                ", convenience='" + convenience + '\'' +
                ", entertainment='" + entertainment + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
