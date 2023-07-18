package com.nd.car.web.dao;

import com.nd.car.web.entity.Status;

import java.util.List;

public interface StatusDao {
    List<Status> findAllStatus();
}
