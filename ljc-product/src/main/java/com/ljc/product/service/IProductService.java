package com.ljc.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljc.product.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljc.product.vo.ProductDetail;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-13
 */
public interface IProductService extends IService<Product> {

    IPage<Product> search(String keyword, Long brandId, Long productCategoryId, IPage<Product> page, Integer sort);

    ProductDetail detail(Long id);
}
