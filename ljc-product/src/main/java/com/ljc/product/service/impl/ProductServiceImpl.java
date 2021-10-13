package com.ljc.product.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljc.product.entity.*;
import com.ljc.product.mapper.ProductMapper;
import com.ljc.product.service.*;
import com.ljc.product.vo.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-13
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private IBrandService brandService;
    @Autowired
    private IProductAttributeService productAttributeService;
    @Autowired
    private IProductAttributeValueService productAttributeValueService;
    @Autowired
    private ISkuStockService skuStockService;
    @Autowired
    private IProductLadderService productLadderService;
    @Autowired
    private IProductFullReductionService productFullReductionService;

    @Override
    public IPage<Product> search(String keyword, Long brandId, Long productCategoryId, IPage<Product> page, Integer sort) {
        LambdaQueryWrapper<Product> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(keyword))
            wrapper.like(Product::getName, keyword);
        if (brandId != null) {
            wrapper.eq(Product::getBrandId, brandId);
        }
        if (productCategoryId != null) {
            wrapper.eq(Product::getProductCategoryId, productCategoryId);
        }
        //1->按新品；2->按销量；3->价格从低到高；4->价格从高到低
        if (sort == 1) {
            wrapper.orderByDesc(Product::getId);
        } else if (sort == 2) {
            wrapper.orderByDesc(Product::getSale);
        } else if (sort == 3) {
            wrapper.orderByAsc(Product::getPrice);
        } else if (sort == 4) {
            wrapper.orderByDesc(Product::getPrice);
        }
        return page(page, wrapper);
    }

    @Override
    public ProductDetail detail(Long id) {
        ProductDetail result = new ProductDetail();
        //获取商品信息
        Product product = getById(id);
        result.setProduct(product);
        //获取品牌信息
        Brand brand = brandService.getById(product.getBrandId());
        result.setBrand(brand);
        //获取商品属性信息
        List<ProductAttribute> productAttributeList = productAttributeService
                .lambdaQuery().eq(ProductAttribute::getProductAttributeCategoryId, product.getProductAttributeCategoryId()).list();
        result.setProductAttributeList(productAttributeList);
        //获取商品属性值信息
        if(CollUtil.isNotEmpty(productAttributeList)){
            List<Long> attributeIds = productAttributeList.stream().map(ProductAttribute::getId).collect(Collectors.toList());
            List<ProductAttributeValue> productAttributeValueList = productAttributeValueService
                    .lambdaQuery().eq(ProductAttributeValue::getProductId, product.getId())
                    .in(ProductAttributeValue::getProductAttributeId, attributeIds)
                    .list();
            result.setProductAttributeValueList(productAttributeValueList);
        }
        //获取商品SKU库存信息
        List<SkuStock> skuStockList = skuStockService.lambdaQuery().eq(SkuStock::getProductId, product.getId()).list();
        result.setSkuStockList(skuStockList);
        //商品阶梯价格设置
        if(product.getPromotionType()==3){
            List<ProductLadder> productLadderList = productLadderService.lambdaQuery().eq(ProductLadder::getProductId, product.getId()).list();
            result.setProductLadderList(productLadderList);
        }
        //商品满减价格设置
        if(product.getPromotionType()==4){
            List<ProductFullReduction> productFullReductionList = productFullReductionService
                    .lambdaQuery().eq(ProductFullReduction::getProductId, product.getId()).list();
            result.setProductFullReductionList(productFullReductionList);
        }
        //商品可用优惠券
        // todo
//        result.setCouponList(portalProductDao.getAvailableCouponList(product.getId(),product.getProductCategoryId()));
        return result;
    }
}
