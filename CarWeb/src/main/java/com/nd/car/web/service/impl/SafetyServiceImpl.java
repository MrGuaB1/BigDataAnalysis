package com.nd.car.web.service.impl;

import com.nd.car.web.dao.SafetyDao;
import com.nd.car.web.entity.Safety;
import com.nd.car.web.service.SafetyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class SafetyServiceImpl implements SafetyService {
    @Autowired
    SafetyDao safetyDao;
    @Override
    public List<Safety> findAllSafety() {
        return safetyDao.findAllSafety();
    }
}
