package com.nd.car.web.dao;

import com.nd.car.web.entity.Drivetrain;

import java.util.List;

public interface DrivetrainDao {
    List<Drivetrain> findAllDrivetrain();
}
