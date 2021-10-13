package com.ljc.product.service.impl;

import com.ljc.product.entity.SkuStock;
import com.ljc.product.mapper.SkuStockMapper;
import com.ljc.product.service.ISkuStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * sku的库存 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-13
 */
@Service
public class SkuStockServiceImpl extends ServiceImpl<SkuStockMapper, SkuStock> implements ISkuStockService {

}
