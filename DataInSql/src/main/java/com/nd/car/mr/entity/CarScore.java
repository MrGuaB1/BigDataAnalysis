package com.nd.car.mr.entity;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarScore implements DBWritable, Writable {

    private String brand;
    private double generalRateScore; //普通费率的得分
    private double comfortScore;
    private double designScore;
    private double valueForMoney; //性价比
    private double styleScore;
    private double reliabilityScore;

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(this.brand);
        out.writeDouble(this.generalRateScore);
        out.writeDouble(this.comfortScore);
        out.writeDouble(this.designScore);
        out.writeDouble(this.valueForMoney);
        out.writeDouble(this.styleScore);
        out.writeDouble(this.reliabilityScore);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.brand=in.readUTF();
        this.generalRateScore=in.readDouble();
        this.comfortScore=in.readDouble();
        this.designScore=in.readDouble();
        this.valueForMoney=in.readDouble();
        this.styleScore=in.readDouble();
        this.reliabilityScore=in.readDouble();
    }

    @Override
    public void write(PreparedStatement ps) throws SQLException {
        ps.setString(1,brand);
        ps.setDouble(2,generalRateScore);
        ps.setDouble(3,comfortScore);
        ps.setDouble(4,designScore);
        ps.setDouble(5,valueForMoney);
        ps.setDouble(6,styleScore);
        ps.setDouble(7,reliabilityScore);
    }

    @Override
    public void readFields(ResultSet rs) throws SQLException {
        this.brand = rs.getString("brand");
        this.generalRateScore=rs.getDouble("generalRateScore");
        this.comfortScore=rs.getDouble("comfortScore");
        this.designScore=rs.getDouble("designScore");
        this.valueForMoney=rs.getDouble("valueForMoney");
        this.styleScore=rs.getDouble("styleScore");
        this.reliabilityScore=rs.getDouble("reliabilityScore");
        this.reliabilityScore=rs.getDouble("reliabilityScore");
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getGeneralRateScore() {
        return generalRateScore;
    }

    public void setGeneralRateScore(double generalRateScore) {
        this.generalRateScore = generalRateScore;
    }

    public double getComfortScore() {
        return comfortScore;
    }

    public void setComfortScore(double comfortScore) {
        this.comfortScore = comfortScore;
    }

    public double getDesignScore() {
        return designScore;
    }

    public void setDesignScore(double designScore) {
        this.designScore = designScore;
    }

    public double getValueForMoney() {
        return valueForMoney;
    }

    public void setValueForMoney(double valueForMoney) {
        this.valueForMoney = valueForMoney;
    }

    public double getStyleScore() {
        return styleScore;
    }

    public void setStyleScore(double styleScore) {
        this.styleScore = styleScore;
    }

    public double getReliabilityScore() {
        return reliabilityScore;
    }

    public void setReliabilityScore(double reliabilityScore) {
        this.reliabilityScore = reliabilityScore;
    }

    public CarScore(String b, double generalRateScore, double comfortScore, double designScore, double valueForMoney, double styleScore, double reliabilityScore) {
        this.brand = b;
        this.generalRateScore = generalRateScore;
        this.comfortScore = comfortScore;
        this.designScore = designScore;
        this.valueForMoney = valueForMoney;
        this.styleScore = styleScore;
        this.reliabilityScore = reliabilityScore;
    }

    public CarScore() {
    }

    @Override
    public String toString() {
        return "CarScore{" +
                "brand=" + brand +
                ", generalRateScore=" + generalRateScore +
                ", comfortScore=" + comfortScore +
                ", designScore=" + designScore +
                ", valueForMoney=" + valueForMoney +
                ", styleScore=" + styleScore +
                ", reliabilityScore=" + reliabilityScore +
                '}';
    }
}

