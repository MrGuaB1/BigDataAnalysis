package com.nd.car.mr.entity;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarBrand implements DBWritable, Writable {
    private String brand_name;
    private int count;
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(brand_name);
        out.writeInt(count);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.brand_name=in.readUTF();
        this.count=in.readInt();
    }

    @Override
    public void write(PreparedStatement ps) throws SQLException {
        ps.setString(1,brand_name);
        ps.setInt(2,count);
    }

    @Override
    public void readFields(ResultSet rs) throws SQLException {
        this.brand_name=rs.getString("brand_name");
        this.count=rs.getInt("count");
    }

    public String getName() {
        return brand_name;
    }

    public void setName(String name) {
        this.brand_name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CarBrand{" +
                "name='" + brand_name + '\'' +
                ", count=" + count +
                '}';
    }

    public CarBrand(String name, int count) {
        this.brand_name = name;
        this.count = count;
    }

    public CarBrand() {
    }
}
