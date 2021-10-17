package com.ljc.product.service.impl;

import com.ljc.product.entity.Category;
import com.ljc.product.mapper.CategoryMapper;
import com.ljc.product.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Cacheable(value = "category",key = "#root.methodName")
    @Override
    public List<Category> listTree() {
        System.out.println("查询了数据库");
        List<Category> categoryList = list();
        return list().stream().filter(category -> category.getParentCid().equals(0L))
                .map(category -> convert(category, categoryList)).collect(Collectors.toList());
    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();

        //递归查询是否还有父节点
        List<Long> parentPath = findParentPath(catelogId, paths);

        //进行一个逆序排列
        Collections.reverse(parentPath);

        return parentPath.toArray(new Long[0]);
    }

    private Category convert(Category category, List<Category> categoryList) {
        Category c = new Category();
        BeanUtils.copyProperties(category, c);
        List<Category> children = categoryList.stream()
                .filter(subItem -> subItem.getParentCid().equals(category.getCatId()))
                .map(subItem -> convert(subItem, categoryList)).collect(Collectors.toList());
        c.setChildren(children);
        return c;
    }

    private List<Long> findParentPath(Long catelogId, List<Long> paths) {

        //1、收集当前节点id
        paths.add(catelogId);

        //根据当前分类id查询信息
        Category byId = this.getById(catelogId);
        //如果当前不是父分类
        if (byId.getParentCid() != 0) {
            findParentPath(byId.getParentCid(), paths);
        }

        return paths;
    }

}
