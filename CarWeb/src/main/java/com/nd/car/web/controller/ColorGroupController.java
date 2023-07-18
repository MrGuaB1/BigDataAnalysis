package com.nd.car.web.controller;

import com.nd.car.web.entity.ColorGroup;
import com.nd.car.web.service.ColorGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //控制层
@CrossOrigin(value = "*",origins ="*") //跨域
@RequestMapping("color") // 注解，可以指定根URL路径
public class ColorGroupController {
    @Autowired
    ColorGroupService colorGroupService;
    @RequestMapping(value = "listGroup")
    List<ColorGroup> listGroup(){
        return colorGroupService.findAllGroup();
    }
}
