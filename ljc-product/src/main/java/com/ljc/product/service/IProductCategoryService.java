package com.ljc.product.service;

import com.ljc.product.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-13
 */
public interface IProductCategoryService extends IService<ProductCategory> {

    List<ProductCategory> categoryTreeList();
}
