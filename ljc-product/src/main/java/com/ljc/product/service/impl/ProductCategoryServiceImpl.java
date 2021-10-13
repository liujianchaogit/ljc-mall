package com.ljc.product.service.impl;

import com.ljc.product.entity.ProductCategory;
import com.ljc.product.mapper.ProductCategoryMapper;
import com.ljc.product.service.IProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-13
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements IProductCategoryService {

    @Override
    public List<ProductCategory> categoryTreeList() {
        List<ProductCategory> list = list();

        return list.stream().filter(productCategory -> productCategory.getParentId().equals(0L))
                .map(patent -> convert(patent, list)).collect(Collectors.toList());
    }

    private ProductCategory convert(ProductCategory patent, List<ProductCategory> list) {
        ProductCategory node = new ProductCategory();
        BeanUtils.copyProperties(patent, node);
        List<ProductCategory> children = list.stream()
                .filter(subItem -> subItem.getParentId().equals(patent.getId()))
                .map(subItem -> convert(subItem, list)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
