package com.nd.car.web.controller;

import com.nd.car.web.entity.Brand;
import com.nd.car.web.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 品牌占比控制层
 * @author 曹珉浩
 * @date 2023/07/16
 */
@RestController //控制层
@CrossOrigin(value = "*",origins ="*") //跨域
@RequestMapping("brand") // 注解，可以指定根URL路径
public class BrandController {
    @Autowired
    private BrandService brandService;
    //查询品牌占比
    @RequestMapping(value = "listBrand")
    public List<Brand> listBrand(){
        List<Brand> allBrand = brandService.findAllBrand();
        return  allBrand;
    }
}
