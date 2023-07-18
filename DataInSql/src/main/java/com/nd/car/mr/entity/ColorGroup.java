package com.nd.car.mr.entity;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ColorGroup implements DBWritable, Writable {
    String exColor;
    String inColor;
    int count;
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(exColor);
        out.writeUTF(inColor);
        out.writeInt(count);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.exColor=in.readUTF();
        this.inColor=in.readUTF();
        this.count=in.readInt();
    }

    @Override
    public void write(PreparedStatement ps) throws SQLException {
        ps.setString(1,exColor);
        ps.setString(2,inColor);
        ps.setInt(3,count);
    }

    @Override
    public void readFields(ResultSet rs) throws SQLException {
        this.exColor=rs.getString("exteriorColor");
        this.inColor=rs.getString("interiorColor");
        this.count=rs.getInt("count");
    }

    public ColorGroup(String exColor, String inColor, int count) {
        this.exColor = exColor;
        this.inColor = inColor;
        this.count = count;
    }

    public ColorGroup() {
    }

    public String getExColor() {
        return exColor;
    }

    public void setExColor(String exColor) {
        this.exColor = exColor;
    }

    public String getInColor() {
        return inColor;
    }

    public void setInColor(String inColor) {
        this.inColor = inColor;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ColorGroup{" +
                "exColor='" + exColor + '\'' +
                ", inColor='" + inColor + '\'' +
                ", count=" + count +
                '}';
    }
}
