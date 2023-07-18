package com.nd.car.web.service;

import com.nd.car.web.entity.PopularBrand;

import java.util.List;

/**
 * 不同价值区间受欢迎品牌持久层接口
 * @author 曹珉浩
 * @date 2023/07/17
 */
public interface PopularBrandService {
    List<PopularBrand> findAllPopularBrand();
}
