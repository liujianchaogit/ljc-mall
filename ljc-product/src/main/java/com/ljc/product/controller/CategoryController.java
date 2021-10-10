package com.ljc.product.controller;


import com.ljc.common.api.R;
import com.ljc.product.entity.Category;
import com.ljc.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品三级分类 前端控制器
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
@RestController
@RequestMapping("/product/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/list/tree")
    public R list(){
        List<Category> entities = categoryService.listWithTree();
        return R.ok(entities);
    }

}

