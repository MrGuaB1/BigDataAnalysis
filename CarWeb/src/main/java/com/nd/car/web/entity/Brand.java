package com.nd.car.web.entity;

import java.io.Serializable;

/**
 * 品牌实体类
 * @author 曹珉浩
 * @date 2023/07/16
 */
public class Brand implements Serializable {
    private String brandName;
    private int count;

    @Override
    public String toString() {
        return "Brand{" +
                "brandName='" + brandName + '\'' +
                ", count=" + count +
                '}';
    }

    public Brand() {
    }

    public Brand(String brandName, int count) {
        this.brandName = brandName;
        this.count = count;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
