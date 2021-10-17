package com.ljc.search.feign;

import com.ljc.common.dto.product.AttrInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("ljc-product")
public interface ProductFeignService {

    @GetMapping("/product/attr/info/{attrId}")
    AttrInfo attrInfo(@PathVariable("attrId") Long attrId);

}
