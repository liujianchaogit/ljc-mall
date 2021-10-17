package com.ljc.product.service;

import com.ljc.product.entity.SkuSaleAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljc.product.vo.SkuItemSaleAttrVo;

import java.util.List;

/**
 * <p>
 * sku销售属性&值 服务类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
public interface ISkuSaleAttrValueService extends IService<SkuSaleAttrValue> {

    List<SkuItemSaleAttrVo> getSaleAttrBySpuId(Long spuId);

    List<String> getSkuSaleAttrValuesAsStringList(Long skuId);
}
