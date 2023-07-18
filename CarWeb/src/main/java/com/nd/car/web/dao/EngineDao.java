package com.nd.car.web.dao;

import com.nd.car.web.entity.Engine;

import java.util.List;

public interface EngineDao {
    List<Engine> findAllEngine();
}
