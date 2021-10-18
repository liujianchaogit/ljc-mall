package com.ljc.order.feign;

import com.ljc.common.api.R;
import com.ljc.order.to.SpuInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("ljc-product")
public interface ProductFeign {

    @GetMapping("/product/spuInfo/skuId/{skuId}")
    R<SpuInfoVo> getSpuInfoBySkuId(@PathVariable("skuId") Long skuId);
}
