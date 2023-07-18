package com.nd.car.web.entity;

import java.io.Serializable;

public class Drivetrain implements Serializable {
    int cyear;
    String drivetrain;
    int count;

    public int getYear() {
        return cyear;
    }

    public void setYear(int year) {
        this.cyear = year;
    }

    public String getDrivetrain() {
        return drivetrain;
    }

    public void setDrivetrain(String drivetrain) {
        this.drivetrain = drivetrain;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Drivetrain(int year, String drivetrain, int count) {
        this.cyear = year;
        this.drivetrain = drivetrain;
        this.count = count;
    }

    public Drivetrain() {
    }

    @Override
    public String toString() {
        return "Drivetrain{" +
                "cyear=" + cyear +
                ", drivetrain='" + drivetrain + '\'' +
                ", count=" + count +
                '}';
    }
}
