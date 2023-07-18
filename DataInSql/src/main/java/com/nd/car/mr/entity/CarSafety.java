package com.nd.car.mr.entity;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarSafety implements DBWritable, Writable {
    String safety;
    int count;
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(safety);
        out.writeInt(count);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.safety=in.readUTF();
        this.count=in.readInt();
    }

    @Override
    public void write(PreparedStatement ps) throws SQLException {
        ps.setString(1,safety);
        ps.setInt(2,count);
    }

    @Override
    public void readFields(ResultSet rs) throws SQLException {
        this.safety=rs.getString("safety");
        this.count=rs.getInt("count");
    }

    public String getSafety() {
        return safety;
    }

    public void setSafety(String safety) {
        this.safety = safety;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CarSafety{" +
                "safety='" + safety + '\'' +
                ", count=" + count +
                '}';
    }

    public CarSafety(String safety, int count) {
        this.safety = safety;
        this.count = count;
    }

    public CarSafety() {
    }
}
