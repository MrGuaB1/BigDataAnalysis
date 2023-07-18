package com.nd.car.web.service.impl;

import com.nd.car.web.dao.EngineDao;
import com.nd.car.web.entity.Engine;
import com.nd.car.web.service.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class EngineServiceImpl implements EngineService {
    @Autowired
    EngineDao engineDao;
    @Override
    public List<Engine> findAllEngine() {
        return engineDao.findAllEngine();
    }
}
