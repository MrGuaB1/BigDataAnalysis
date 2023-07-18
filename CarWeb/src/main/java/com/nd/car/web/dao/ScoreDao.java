package com.nd.car.web.dao;

import com.nd.car.web.entity.Score;

import java.util.List;

public interface ScoreDao {
    List<Score> findAllScore();
}
