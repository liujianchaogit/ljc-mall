package com.ljc.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljc.product.entity.SkuSaleAttrValue;
import com.ljc.product.vo.SkuItemSaleAttrVo;

import java.util.List;

/**
 * <p>
 * sku销售属性&值 Mapper 接口
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
public interface SkuSaleAttrValueMapper extends BaseMapper<SkuSaleAttrValue> {

    List<SkuItemSaleAttrVo> getSaleAttrBySpuId(Long spuId);

    List<String> getSkuSaleAttrValuesAsStringList(Long skuId);
}
