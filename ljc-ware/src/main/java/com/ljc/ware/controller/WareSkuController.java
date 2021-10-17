package com.ljc.ware.controller;

import com.ljc.common.dto.ware.SkuStock;
import com.ljc.ware.service.IWareSkuService;
import com.ljc.ware.vo.WareSkuLockVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ware/wareSku")
public class WareSkuController {

    private final IWareSkuService wareSkuService;

    public WareSkuController(IWareSkuService wareSkuService) {
        this.wareSkuService = wareSkuService;
    }

    @PostMapping("/skuStock")
    public List<SkuStock> getSkuStockList(@RequestBody List<Long> skuIds) {
        return wareSkuService.getSkuStockList(skuIds);
    }

    /**
     * 锁定库存
     * @param vo
     *
     * 库存解锁的场景
     *      1）、下订单成功，订单过期没有支付被系统自动取消或者被用户手动取消，都要解锁库存
     *      2）、下订单成功，库存锁定成功，接下来的业务调用失败，导致订单回滚。之前锁定的库存就要自动解锁
     *      3）、
     *
     * @return
     */
    @PostMapping(value = "/lock/order")
    public boolean orderLockStock(@RequestBody WareSkuLockVo vo) {
        return wareSkuService.orderLockStock(vo);
    }

}

