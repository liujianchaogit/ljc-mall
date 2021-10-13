package com.ljc.product.controller;


import com.ljc.product.entity.ProductCategory;
import com.ljc.product.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-13
 */
@RestController
@RequestMapping("/product/productCategory")
public class ProductCategoryController {

    @Autowired
    private IProductCategoryService productCategoryService;

    @GetMapping("/categoryTreeList")
    public List<ProductCategory> categoryTreeList() {
        return productCategoryService.categoryTreeList();
    }

}

