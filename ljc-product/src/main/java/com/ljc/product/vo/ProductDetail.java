package com.ljc.product.vo;

import com.ljc.product.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 前台商品详情
 * Created by macro on 2020/4/6.
 */
@Getter
@Setter
public class ProductDetail {
    private Product product;
    private Brand brand;
    private List<ProductAttribute> productAttributeList;
    private List<ProductAttributeValue> productAttributeValueList;
    private List<SkuStock> skuStockList;
    private List<ProductLadder> productLadderList;
    private List<ProductFullReduction> productFullReductionList;
//    private List<SmsCoupon> couponList;
}
