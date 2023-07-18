package com.nd.car.mr.entity;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarStatus implements DBWritable, Writable {
    int cyear;
    int newCount;
    int usedCount;
    int certifiedCount; //认证二手车
    int otherCount;
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(cyear);
        out.writeInt(newCount);
        out.writeInt(usedCount);
        out.writeInt(certifiedCount);
        out.writeInt(otherCount);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.cyear=in.readInt();
        this.newCount=in.readInt();
        this.usedCount=in.readInt();
        this.certifiedCount=in.readInt();
        this.otherCount=in.readInt();
    }

    @Override
    public void write(PreparedStatement ps) throws SQLException {
        ps.setInt(1,cyear);
        ps.setInt(2,newCount);
        ps.setInt(3,usedCount);
        ps.setInt(4,certifiedCount);
        ps.setInt(5,otherCount);
    }

    @Override
    public void readFields(ResultSet rs) throws SQLException {
        this.cyear=rs.getInt("cyear");
        this.newCount=rs.getInt("newCount");
        this.usedCount=rs.getInt("usedCount");
        this.certifiedCount=rs.getInt("certifiedCount");
        this.otherCount=rs.getInt("otherCount");
    }

    public int getCyear() {
        return cyear;
    }

    public void setCyear(int cyear) {
        this.cyear = cyear;
    }

    public int getNewCount() {
        return newCount;
    }

    public void setNewCount(int newCount) {
        this.newCount = newCount;
    }

    public int getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(int usedCount) {
        this.usedCount = usedCount;
    }

    public int getCertifiedCount() {
        return certifiedCount;
    }

    public void setCertifiedCount(int certifiedCount) {
        this.certifiedCount = certifiedCount;
    }

    public int getOtherCount() {
        return otherCount;
    }

    public void setOtherCount(int otherCount) {
        this.otherCount = otherCount;
    }

    @Override
    public String toString() {
        return "CarStatus{" +
                "cyear=" + cyear +
                ", newCount=" + newCount +
                ", usedCount=" + usedCount +
                ", certifiedCount=" + certifiedCount +
                ", otherCount=" + otherCount +
                '}';
    }

    public CarStatus(int cyear, int newCount, int usedCount, int certifiedCount, int otherCount) {
        this.cyear = cyear;
        this.newCount = newCount;
        this.usedCount = usedCount;
        this.certifiedCount = certifiedCount;
        this.otherCount = otherCount;
    }

    public CarStatus() {
    }
}
