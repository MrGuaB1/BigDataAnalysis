package com.nd.car.mr.entity;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarFuel implements DBWritable, Writable {
    private int cyear;
    private String fuelType;
    private int count;
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(cyear);
        out.writeUTF(fuelType);
        out.writeInt(count);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.cyear = in.readInt();
        this.fuelType = in.readUTF();
        this.count = in.readInt();
    }

    @Override
    public void write(PreparedStatement ps) throws SQLException {
        ps.setInt(1,cyear);
        ps.setString(2,fuelType);
        ps.setInt(3,count);
    }

    @Override
    public void readFields(ResultSet rs) throws SQLException {
        this.cyear = rs.getInt("cyear");
        this.fuelType = rs.getString("fuelType");
        this.count = rs.getInt("count");
    }

    public CarFuel(int cyear, String fuelType, int count) {
        this.cyear = cyear;
        this.fuelType = fuelType;
        this.count = count;
    }

    public CarFuel() {
    }

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

    @Override
    public String toString() {
        return "CarFuel{" +
                "cyear=" + cyear +
                ", fuelType='" + fuelType + '\'' +
                ", count=" + count +
                '}';
    }
}
