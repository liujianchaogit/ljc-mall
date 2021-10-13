package com.ljc.product.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljc.product.entity.Product;
import com.ljc.product.service.IProductService;
import com.ljc.product.vo.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-13
 */
@Controller
@RequestMapping("/product/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public IPage<Product> search(@RequestParam(required = false) String keyword,
                                 @RequestParam(required = false) Long brandId,
                                 @RequestParam(required = false) Long productCategoryId,
                                 @RequestParam(required = false, defaultValue = "1") Integer current,
                                 @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                 @RequestParam(required = false, defaultValue = "0") Integer sort) {
        return productService.search(keyword, brandId, productCategoryId, new Page<>(current, pageSize), sort);
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ProductDetail detail(@PathVariable Long id) {
        return productService.detail(id);
    }

}

