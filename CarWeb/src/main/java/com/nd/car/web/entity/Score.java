package com.nd.car.web.entity;

import java.io.Serializable;

public class Score implements Serializable {
    String brand;
    String generalRateScore;
    String comfortScore;
    String designScore;
    String valueForMoney;
    String styleScore;
    String reliabilityScore;

    public Score(String brand, String generalRateScore, String comfortScore, String designScore, String valueForMoney, String styleScore, String reliabilityScore) {
        this.brand = brand;
        this.generalRateScore = generalRateScore;
        this.comfortScore = comfortScore;
        this.designScore = designScore;
        this.valueForMoney = valueForMoney;
        this.styleScore = styleScore;
        this.reliabilityScore = reliabilityScore;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getGeneralRateScore() {
        return generalRateScore;
    }

    public void setGeneralRateScore(String generalRateScore) {
        this.generalRateScore = generalRateScore;
    }

    public String getComfortScore() {
        return comfortScore;
    }

    public void setComfortScore(String comfortScore) {
        this.comfortScore = comfortScore;
    }

    public String getDesignScore() {
        return designScore;
    }

    public void setDesignScore(String designScore) {
        this.designScore = designScore;
    }

    public String getValueForMoney() {
        return valueForMoney;
    }

    public void setValueForMoney(String valueForMoney) {
        this.valueForMoney = valueForMoney;
    }

    public String getStyleScore() {
        return styleScore;
    }

    public void setStyleScore(String styleScore) {
        this.styleScore = styleScore;
    }

    public String getReliabilityScore() {
        return reliabilityScore;
    }

    public void setReliabilityScore(String reliabilityScore) {
        this.reliabilityScore = reliabilityScore;
    }

    @Override
    public String toString() {
        return "Score{" +
                "brand='" + brand + '\'' +
                ", generalRateScore='" + generalRateScore + '\'' +
                ", comfortScore='" + comfortScore + '\'' +
                ", designScore='" + designScore + '\'' +
                ", valueForMoney='" + valueForMoney + '\'' +
                ", styleScore='" + styleScore + '\'' +
                ", reliabilityScore='" + reliabilityScore + '\'' +
                '}';
    }

    public Score() {
    }
}
