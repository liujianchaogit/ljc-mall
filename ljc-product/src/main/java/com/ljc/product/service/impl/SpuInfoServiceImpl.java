package com.ljc.product.service.impl;

import com.ljc.product.entity.Brand;
import com.ljc.product.entity.SkuInfo;
import com.ljc.product.entity.SpuInfo;
import com.ljc.product.mapper.SpuInfoMapper;
import com.ljc.product.service.IBrandService;
import com.ljc.product.service.ISkuInfoService;
import com.ljc.product.service.ISpuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * spu信息 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo> implements ISpuInfoService {

    @Autowired
    private ISkuInfoService skuInfoService;
    @Autowired
    private IBrandService brandService;

    @Override
    public SpuInfo getSpuInfoBySkuId(Long skuId) {
        SpuInfo spuInfo = getById(skuInfoService.getById(skuId).getSpuId());
        spuInfo.setBrandName(brandService.getById(spuInfo.getBrandId()).getName());
        return spuInfo;
    }

}
