package com.nd.car.web.dao;

import com.nd.car.web.entity.InteriorColor;

import java.util.List;

public interface InteriorDao {
    List<InteriorColor> findAllInteriorColor();
}
