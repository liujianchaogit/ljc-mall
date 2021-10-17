package com.ljc.product.service;

import com.ljc.product.entity.SpuInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * spu信息 服务类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
public interface ISpuInfoService extends IService<SpuInfo> {

    SpuInfo getSpuInfoBySkuId(Long skuId);
}
