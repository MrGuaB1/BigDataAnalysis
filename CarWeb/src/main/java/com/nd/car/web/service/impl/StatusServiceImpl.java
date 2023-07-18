package com.nd.car.web.service.impl;

import com.nd.car.web.dao.StatusDao;
import com.nd.car.web.entity.Status;
import com.nd.car.web.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class StatusServiceImpl implements StatusService {
    @Autowired
    StatusDao statusDao;
    @Override
    public List<Status> findAllStatus() {
        return statusDao.findAllStatus();
    }
}
