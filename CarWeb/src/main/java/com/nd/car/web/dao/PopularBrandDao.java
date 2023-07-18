package com.nd.car.web.dao;

import com.nd.car.web.entity.PopularBrand;
import java.util.List;

/**
 * 不同价位区间最受欢迎的品牌及其基本信息持久层接口
 * @author 曹珉浩
 * @date 2023/07/17
 */
public interface PopularBrandDao {
    List<PopularBrand> findAllPopularBrand();
}
