package com.ljc.search.feign;

import com.ljc.common.api.R;
import com.ljc.search.vo.AttrResponseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedHashMap;

@FeignClient("ljc-product")
public interface ProductFeignService {

    @GetMapping("/product/attr/info/{attrId}")
    R<AttrResponseVo> attrInfo(@PathVariable("attrId") Long attrId);

}
