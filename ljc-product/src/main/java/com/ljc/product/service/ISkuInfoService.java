package com.ljc.product.service;

import com.ljc.product.entity.SkuInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljc.product.vo.SkuItemVo;

import java.util.concurrent.ExecutionException;

/**
 * <p>
 * sku信息 服务类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
public interface ISkuInfoService extends IService<SkuInfo> {

    SkuItemVo item(Long skuId) throws ExecutionException, InterruptedException;
}
