package com.nd.car.web.service.impl;

import com.nd.car.web.dao.InteriorDao;
import com.nd.car.web.entity.InteriorColor;
import com.nd.car.web.service.InteriorColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service //业务层
@Transactional //支持事务
public class InteriorColorServiceImpl implements InteriorColorService {
    @Autowired
    InteriorDao interiorDao;
    @Override
    public List<InteriorColor> findAllInteriorColor() {
        return interiorDao.findAllInteriorColor();
    }
}
