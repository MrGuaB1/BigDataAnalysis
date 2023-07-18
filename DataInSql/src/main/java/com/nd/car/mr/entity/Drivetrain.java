package com.nd.car.mr.entity;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Drivetrain implements DBWritable, Writable {
    private int cyear;
    private String drivetrain;
    private int count;
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(cyear);
        out.writeUTF(drivetrain);
        out.writeInt(count);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.cyear = in.readInt();
        this.drivetrain = in.readUTF();
        this.count = in.readInt();
    }

    @Override
    public void write(PreparedStatement ps) throws SQLException {
        ps.setInt(1,cyear);
        ps.setString(2,drivetrain);
        ps.setInt(3,count);
    }

    @Override
    public void readFields(ResultSet rs) throws SQLException {
        this.cyear = rs.getInt("cyear");
        this.drivetrain = rs.getString("drivetrain");
        this.count = rs.getInt("count");
    }

    public int getCyear() {
        return cyear;
    }

    public void setCyear(int cyear) {
        this.cyear = cyear;
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

    public Drivetrain(int cyear, String drivetrain, int count) {
        this.cyear = cyear;
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
