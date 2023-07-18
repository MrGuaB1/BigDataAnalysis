package com.nd.car.web.service.impl;

import com.nd.car.web.dao.ExteriorDao;
import com.nd.car.web.entity.ExteriorColor;
import com.nd.car.web.service.ExteriorColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service //业务层
@Transactional //支持事务
public class ExteriorColorServiceImpl implements ExteriorColorService {
    @Autowired
    ExteriorDao exteriorDao;
    @Override
    public List<ExteriorColor> findAllExteriorColor() {
        return exteriorDao.findAllExteriorColor();
    }
}
