package com.nd.car.web.controller;

import com.nd.car.web.entity.InteriorColor;
import com.nd.car.web.service.InteriorColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //控制层
@CrossOrigin(value = "*",origins ="*") //跨域
@RequestMapping("color") // 注解，可以指定根URL路径
public class InteriorColorController {
    @Autowired
    InteriorColorService interiorColorService;
    @RequestMapping(value = "listInterior")
    List<InteriorColor> listInterior(){
        return interiorColorService.findAllInteriorColor();
    }
}
