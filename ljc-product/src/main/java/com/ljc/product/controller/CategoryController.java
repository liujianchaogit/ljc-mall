package com.ljc.product.controller;

import com.ljc.product.entity.Category;
import com.ljc.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/listTree")
    public List<Category> listTree() {
        return categoryService.listTree();
    }

}

