package com.ljc.product.service.impl;

import com.ljc.product.entity.ProductFullReduction;
import com.ljc.product.mapper.ProductFullReductionMapper;
import com.ljc.product.service.IProductFullReductionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品满减表(只针对同商品) 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-13
 */
@Service
public class ProductFullReductionServiceImpl extends ServiceImpl<ProductFullReductionMapper, ProductFullReduction> implements IProductFullReductionService {

}
