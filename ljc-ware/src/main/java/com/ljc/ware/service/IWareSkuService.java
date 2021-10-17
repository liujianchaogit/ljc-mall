package com.ljc.ware.service;

import com.ljc.common.dto.ware.SkuStock;
import com.ljc.ware.entity.WareSku;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljc.ware.vo.WareSkuLockVo;

import java.util.List;

/**
 * <p>
 * 商品库存 服务类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-17
 */
public interface IWareSkuService extends IService<WareSku> {

    List<SkuStock> getSkuStockList(List<Long> skuIds);

    boolean orderLockStock(WareSkuLockVo vo);
}
