package com.ljc.cart.fegin;

import com.ljc.cart.po.SkuInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@FeignClient("ljc-product")
public interface ProductFeign {

    @GetMapping("/product/skuInfo/{id}")
    SkuInfo getSkuInfo(@PathVariable("id") Long id);

    @GetMapping("/product/skuSaleAttrValue/stringList/{id}")
    List<String> getSkuSaleAttrValues(@PathVariable("id") Long id);

    @GetMapping("/product/skuInfo/{id}/price")
    BigDecimal getPrice(@PathVariable("id") Long id);
}
