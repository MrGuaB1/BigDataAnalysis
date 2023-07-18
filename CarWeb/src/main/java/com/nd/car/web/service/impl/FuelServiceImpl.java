package com.nd.car.web.service.impl;

import com.nd.car.web.dao.FuelDao;
import com.nd.car.web.entity.Fuel;
import com.nd.car.web.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class FuelServiceImpl implements FuelService {
    @Autowired
    FuelDao fuelDao;
    @Override
    public List<Fuel> findAllFuel() {
        return fuelDao.findAllFuel();
    }
}
