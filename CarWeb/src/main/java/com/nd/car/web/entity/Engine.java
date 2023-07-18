package com.nd.car.web.entity;

import java.io.Serializable;

public class Engine implements Serializable {
    String cengine;
    int count;

    public Engine(String cengine, int count) {
        this.cengine = cengine;
        this.count = count;
    }

    public Engine() {
    }

    public String getCengine() {
        return cengine;
    }

    public void setCengine(String cengine) {
        this.cengine = cengine;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "engine{" +
                "cengine='" + cengine + '\'' +
                ", count=" + count +
                '}';
    }
}
