package com.nd.car.mr.entity;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarColor implements DBWritable, Writable {
    String color;
    int count;
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(color);
        out.writeInt(count);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.color=in.readUTF();
        this.count=in.readInt();
    }

    @Override
    public void write(PreparedStatement ps) throws SQLException {
        ps.setString(1,color);
        ps.setInt(2,count);
    }

    @Override
    public void readFields(ResultSet rs) throws SQLException {
        this.color=rs.getString("color");
        this.count=rs.getInt("count");
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CarColor(String color) {
        this.color = color;
    }

    public CarColor(String color, int count) {
        this.color = color;
        this.count = count;
    }

    public CarColor() {
    }
}
