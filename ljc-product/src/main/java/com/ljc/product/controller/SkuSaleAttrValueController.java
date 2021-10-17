package com.ljc.product.controller;


import com.ljc.common.annotation.NoR;
import com.ljc.product.service.ISkuSaleAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * sku销售属性&值 前端控制器
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
@RestController
@RequestMapping("/product/skuSaleAttrValue")
public class SkuSaleAttrValueController {

    @Autowired
    private ISkuSaleAttrValueService skuSaleAttrValueService;

    @GetMapping(value = "/stringList/{id}")
    @NoR
    public List<String> getSkuSaleAttrValues(@PathVariable("id") Long id) {
        return skuSaleAttrValueService.getSkuSaleAttrValuesAsStringList(id);
    }

}

