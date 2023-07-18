package com.nd.car.web.service.impl;

import com.nd.car.web.dao.ScoreDao;
import com.nd.car.web.entity.Score;
import com.nd.car.web.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    ScoreDao scoreDao;
    @Override
    public List<Score> findAllScore() {
        return scoreDao.findAllScore();
    }
}
