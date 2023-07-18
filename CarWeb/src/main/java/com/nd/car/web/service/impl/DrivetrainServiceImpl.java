package com.nd.car.web.service.impl;

import com.nd.car.web.dao.DrivetrainDao;
import com.nd.car.web.entity.Drivetrain;
import com.nd.car.web.service.DrivetrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service //业务层
@Transactional //支持事务
public class DrivetrainServiceImpl implements DrivetrainService {
    @Autowired
    DrivetrainDao drivetrainDao;
    @Override
    public List<Drivetrain> findAllDrivetrain() {
        return drivetrainDao.findAllDrivetrain();
    }
}
