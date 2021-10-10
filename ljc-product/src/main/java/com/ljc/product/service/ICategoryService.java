package com.ljc.product.service;

import com.ljc.product.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljc.product.vo.Catelog2Vo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品三级分类 服务类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
public interface ICategoryService extends IService<Category> {

    List<Category> listWithTree();

    List<Category> getLevel1Categorys();

    Map<String, List<Catelog2Vo>> getCatalogJson();

    Long[] findCatelogPath(Long catelogId);
}
