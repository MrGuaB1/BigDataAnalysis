package com.nd.car.web.dao;

import com.nd.car.web.entity.Fuel;

import java.util.List;

public interface FuelDao {
    List<Fuel> findAllFuel();
}
