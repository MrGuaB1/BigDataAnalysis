package com.nd.car.web.dao;
import com.nd.car.web.entity.Brand;

import java.util.List;

/**
 * 品牌市场占比持久层接口
 * @author 曹珉浩
 * @date 2023/07/16
 */
public interface BrandDao {
    List<Brand> findAllBrand();
}
