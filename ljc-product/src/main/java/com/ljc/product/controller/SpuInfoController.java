package com.ljc.product.controller;


import com.ljc.product.entity.SpuInfo;
import com.ljc.product.service.ISpuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * spu信息 前端控制器
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
@Controller
@RequestMapping("/product/spuInfo")
public class SpuInfoController {

    @Autowired
    private ISpuInfoService spuInfoService;

    @GetMapping(value = "/skuId/{skuId}")
    public SpuInfo getSpuInfoBySkuId(@PathVariable("skuId") Long skuId) {
        return spuInfoService.getSpuInfoBySkuId(skuId);
    }
}

