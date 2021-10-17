package com.ljc.product.controller;

import com.ljc.common.annotation.NoR;
import com.ljc.product.entity.SkuInfo;
import com.ljc.product.service.ISkuInfoService;
import com.ljc.product.vo.SkuItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/product/skuInfo")
public class SkuInfoController {

    @Autowired
    private ISkuInfoService skuInfoService;

    @GetMapping("/{id}")
    @NoR
    public SkuInfo get(@PathVariable long id) {
        return skuInfoService.getById(id);
    }

    @GetMapping("/detail")
    public SkuItemVo get(@RequestParam Long skuId) throws ExecutionException, InterruptedException {
        return skuInfoService.detail(skuId);
    }

    @GetMapping("/{id}/price")
    @NoR
    public BigDecimal getPrice(@PathVariable("id") Long id) {
        return skuInfoService.getById(id).getPrice();
    }

}

