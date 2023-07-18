package com.nd.car.web.controller;

import com.nd.car.web.entity.PopularBrand;
import com.nd.car.web.service.PopularBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 不同价值区间受欢迎的品牌控制层
 * @author 曹珉浩
 * @date 2023/07/17
 */
@RestController //控制层
@CrossOrigin(value = "*",origins ="*") //跨域
@RequestMapping("brand") // 注解，可以指定根URL路径
public class PopularBrandController {
    @Autowired
    PopularBrandService popularBrandService;
    @RequestMapping(value = "listPopularBrand")
    public List<PopularBrand> listPopularBrand(){
        return popularBrandService.findAllPopularBrand();
    }
}
