package com.nd.car.web.controller;

import com.nd.car.web.entity.Score;
import com.nd.car.web.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //控制层
@CrossOrigin(value = "*",origins ="*") //跨域
@RequestMapping("score") // 注解，可以指定根URL路径
public class ScoreController {
    @Autowired
    ScoreService scoreService;
    @RequestMapping(value = "listScore")
    List<Score> listScore(){
        return scoreService.findAllScore();
    }
}
