package com.nd.car.web.service.impl;

import com.nd.car.web.dao.PopularBrandDao;
import com.nd.car.web.entity.PopularBrand;
import com.nd.car.web.service.PopularBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service //业务层
@Transactional //支持事务
public class PopularBrandServiceImpl implements PopularBrandService {
    @Autowired
    private PopularBrandDao popularBrandDao;
    @Override
    public List<PopularBrand> findAllPopularBrand() {
        return popularBrandDao.findAllPopularBrand();
    }
}
