package com.nd.car.web.controller;

import com.nd.car.web.entity.Status;
import com.nd.car.web.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //控制层
@CrossOrigin(value = "*",origins ="*") //跨域
@RequestMapping("status") // 注解，可以指定根URL路径
public class StatusController {
    @Autowired
    StatusService statusService;
    @RequestMapping(value = "listStatus")
    List<Status> listStatus(){
        return statusService.findAllStatus();
    }
}
