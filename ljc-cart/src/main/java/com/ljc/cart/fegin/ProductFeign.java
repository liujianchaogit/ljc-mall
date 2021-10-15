package com.ljc.cart.fegin;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("ljc-product")
public interface ProductFeign {
}
