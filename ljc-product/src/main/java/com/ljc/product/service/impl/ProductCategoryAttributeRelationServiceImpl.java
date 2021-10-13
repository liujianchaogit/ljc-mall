package com.ljc.product.service.impl;

import com.ljc.product.entity.ProductCategoryAttributeRelation;
import com.ljc.product.mapper.ProductCategoryAttributeRelationMapper;
import com.ljc.product.service.IProductCategoryAttributeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-13
 */
@Service
public class ProductCategoryAttributeRelationServiceImpl extends ServiceImpl<ProductCategoryAttributeRelationMapper, ProductCategoryAttributeRelation> implements IProductCategoryAttributeRelationService {

}
