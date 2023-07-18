package com.nd.car.web.service.impl;

import com.nd.car.web.dao.ColorGroupDao;
import com.nd.car.web.entity.ColorGroup;
import com.nd.car.web.service.ColorGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class ColorGroupServiceImpl implements ColorGroupService {
    @Autowired
    ColorGroupDao colorGroupDao;
    @Override
    public List<ColorGroup> findAllGroup() {
        return colorGroupDao.findAllGroup();
    }
}
