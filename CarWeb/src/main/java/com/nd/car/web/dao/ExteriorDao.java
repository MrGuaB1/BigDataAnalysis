package com.nd.car.web.dao;

import com.nd.car.web.entity.ExteriorColor;

import java.util.List;

public interface ExteriorDao {
    List<ExteriorColor> findAllExteriorColor();
}
