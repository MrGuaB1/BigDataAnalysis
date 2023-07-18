package com.nd.car.web.service.impl;

import com.nd.car.web.dao.MPGDao;
import com.nd.car.web.entity.MPG;
import com.nd.car.web.service.MPGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class MPGServiceImpl implements MPGService {
    @Autowired
    MPGDao mpgDao;
    @Override
    public List<MPG> findAllMPG() {
        return mpgDao.findAllMPG();
    }
}
