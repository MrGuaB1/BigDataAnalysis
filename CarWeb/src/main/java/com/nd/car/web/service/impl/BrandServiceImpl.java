package com.nd.car.web.service.impl;

import com.nd.car.web.entity.Brand;
import com.nd.car.web.dao.BrandDao;
import com.nd.car.web.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //业务层
@Transactional //支持事务
public class BrandServiceImpl implements BrandService {
    @Autowired //自动装配
    private BrandDao brandDao; //Dao持久层对象
    @Override
    public List<Brand> findAllBrand() {
        return brandDao.findAllBrand();
    }
}
