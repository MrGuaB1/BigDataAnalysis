package com.nd.car.web.service;

import com.nd.car.web.entity.Brand;

import java.util.List;

/**
 * 品牌占比业务层接口
 * @author 曹珉浩
 * @date 2023/07/16
 */
public interface BrandService {
    List<Brand> findAllBrand();
}
