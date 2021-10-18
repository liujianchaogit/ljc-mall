package com.ljc.order.feign;

import com.ljc.common.api.R;
import com.ljc.order.vo.OrderItemVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("ljc-cart")
public interface CartFeign {

    @GetMapping("/cart/list")
    R<List<OrderItemVo>> list();

}
