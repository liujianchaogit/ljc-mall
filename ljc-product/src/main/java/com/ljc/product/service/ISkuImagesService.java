package com.ljc.product.service;

import com.ljc.product.entity.SkuImages;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * sku图片 服务类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
public interface ISkuImagesService extends IService<SkuImages> {

    List<SkuImages> getImagesBySkuId(Long skuId);
}
