package com.ljc.product.service;

import com.ljc.product.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品三级分类 服务类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
public interface ICategoryService extends IService<Category> {

    List<Category> listTree();

    Long[] findCatelogPath(Long catelogId);
}
