package com.ljc.order.feign;

import com.ljc.common.api.R;
import com.ljc.common.dto.ware.SkuStock;
import com.ljc.common.dto.ware.FareVo;
import com.ljc.order.vo.WareSkuLockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("ljc-ware")
public interface WareFeign {

    @PostMapping("/ware/wareSku/skuStock")
    R<List<SkuStock>> getSkuStockList(@RequestBody List<Long> skuIds);

    @GetMapping("/ware/wareInfo/fare")
    R<FareVo> getFare(@RequestParam("addrId")Long addrId);

    @PostMapping("/ware/wareSku/lock/order")
    R<Object> orderLockStock(@RequestBody WareSkuLockVo lockVo);
}
