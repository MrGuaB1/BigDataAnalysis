package com.nd.car.web.entity;

import java.io.Serializable;

public class MPG implements Serializable {
    String mpg;
    int count;

    public String getMpg() {
        return mpg;
    }

    public void setMpg(String mpg) {




        this.mpg = mpg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public MPG(String MPG, int count) {
        this.mpg = MPG;
        this.count = count;
    }

    public MPG() {
    }

    @Override
    public String toString() {
        return "MPG{" +
                "mpg='" + mpg + '\'' +
                ", count=" + count +
                '}';
    }
}
