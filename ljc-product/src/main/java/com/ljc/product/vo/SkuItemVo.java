package com.ljc.product.vo;

import com.ljc.product.entity.SkuImages;
import com.ljc.product.entity.SkuInfo;
import com.ljc.product.entity.SpuInfoDesc;
import lombok.Data;

import java.util.List;

@Data
public class SkuItemVo {

    //1、sku基本信息的获取  pms_sku_info
    private SkuInfo info;

    private boolean hasStock = true;

    //2、sku的图片信息    pms_sku_images
    private List<SkuImages> images;

    //3、获取spu的销售属性组合
    private List<SkuItemSaleAttrVo> saleAttr;

    //4、获取spu的介绍
    private SpuInfoDesc desc;

    //5、获取spu的规格参数信息
    private List<SpuItemAttrGroupVo> groupAttrs;

    //6、秒杀商品的优惠信息
    private SeckillSkuVo seckillSkuVo;

}
