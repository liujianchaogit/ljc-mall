package com.ljc.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljc.product.entity.SkuSaleAttrValue;
import com.ljc.product.mapper.SkuSaleAttrValueMapper;
import com.ljc.product.service.ISkuSaleAttrValueService;
import com.ljc.product.vo.SkuItemSaleAttrVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * sku销售属性&值 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
@Service
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueMapper, SkuSaleAttrValue> implements ISkuSaleAttrValueService {

    @Override
    public List<SkuItemSaleAttrVo> getSaleAttrBySpuId(Long spuId) {
        return baseMapper.getSaleAttrBySpuId(spuId);
    }
}
