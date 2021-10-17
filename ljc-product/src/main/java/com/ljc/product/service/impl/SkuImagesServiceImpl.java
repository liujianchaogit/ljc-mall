package com.ljc.product.service.impl;

import com.ljc.product.entity.SkuImages;
import com.ljc.product.mapper.SkuImagesMapper;
import com.ljc.product.service.ISkuImagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * sku图片 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
@Service
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesMapper, SkuImages> implements ISkuImagesService {

    @Override
    public List<SkuImages> getImagesBySkuId(Long skuId) {
        return lambdaQuery().eq(SkuImages::getSkuId, skuId).list();
    }
}
