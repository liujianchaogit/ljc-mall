package com.ljc.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljc.common.dto.product.AttrInfo;
import com.ljc.product.entity.Attr;
import com.ljc.product.entity.AttrAttrgroupRelation;
import com.ljc.product.entity.AttrGroup;
import com.ljc.product.entity.Category;
import com.ljc.product.mapper.AttrMapper;
import com.ljc.product.service.IAttrAttrgroupRelationService;
import com.ljc.product.service.IAttrGroupService;
import com.ljc.product.service.IAttrService;
import com.ljc.product.service.ICategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements IAttrService {

    @Autowired
    private IAttrAttrgroupRelationService attrAttrgroupRelationService;
    @Autowired
    private IAttrGroupService attrGroupService;
    @Autowired
    private ICategoryService categoryService;

    @Override
    public AttrInfo info(Long id) {
        Attr attr = getById(id);
        AttrInfo attrInfo = new AttrInfo();
        BeanUtils.copyProperties(attr, attrInfo);
        if (attr.getAttrType() == 1) {
            AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationService.lambdaQuery()
                    .eq(AttrAttrgroupRelation::getAttrId, id).getEntity();
            if (attrAttrgroupRelation != null) {
                attrInfo.setAttrGroupId(attrAttrgroupRelation.getAttrGroupId());
                //获取分组名称
                AttrGroup attrGroup = attrGroupService.getById(attrAttrgroupRelation.getAttrGroupId());
                if (attrGroup != null) {
                    attrInfo.setGroupName(attrGroup.getAttrGroupName());
                }
            }
        }
        Long catelogId = attr.getCatelogId();
        Long[] catelogPath = categoryService.findCatelogPath(catelogId);
        attrInfo.setCatelogPath(catelogPath);
        Category categoryEntity = categoryService.getById(catelogId);
        if (categoryEntity != null) {
            attrInfo.setCatelogName(categoryEntity.getName());
        }
        return attrInfo;
    }
}
