package com.ljc.product.service.impl;

import com.ljc.product.entity.ProductAttribute;
import com.ljc.product.mapper.ProductAttributeMapper;
import com.ljc.product.service.IProductAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-13
 */
@Service
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements IProductAttributeService {

}
