package com.nd.car.web.controller;

import com.nd.car.web.entity.Safety;
import com.nd.car.web.service.SafetyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //控制层
@CrossOrigin(value = "*",origins ="*") //跨域
@RequestMapping("safety") // 注解，可以指定根URL路径
public class SafetyController {
    @Autowired
    SafetyService safetyService;
    @RequestMapping(value = "listSafety")
    List<Safety> listSafety(){
        return safetyService.findAllSafety();
    }
}
