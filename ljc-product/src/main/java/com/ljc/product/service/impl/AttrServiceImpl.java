package com.ljc.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljc.product.entity.Attr;
import com.ljc.product.entity.AttrAttrgroupRelation;
import com.ljc.product.entity.AttrGroup;
import com.ljc.product.entity.Category;
import com.ljc.product.mapper.AttrAttrgroupRelationMapper;
import com.ljc.product.mapper.AttrGroupMapper;
import com.ljc.product.mapper.AttrMapper;
import com.ljc.product.service.IAttrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljc.product.service.ICategoryService;
import com.ljc.product.vo.AttrRespVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品属性 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements IAttrService {

    @Autowired
    private AttrAttrgroupRelationMapper relationDao;
    @Autowired
    private AttrGroupMapper attrGroupDao;
    @Autowired
    private ICategoryService categoryService;

    @Override
    public AttrRespVo getAttrInfo(Long attrId) {
        Attr attrEntity = this.getById(attrId);

        //查询分组信息
        AttrRespVo respVo = new AttrRespVo();
        BeanUtils.copyProperties(attrEntity,respVo);

        //判断是否是基本类型
        if (attrEntity.getAttrType() == 1) {
            //1、设置分组信息
            AttrAttrgroupRelation relationEntity = relationDao.selectOne
                    (new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", attrId));
            if (relationEntity != null) {
                respVo.setAttrGroupId(relationEntity.getAttrGroupId());
                //获取分组名称
                AttrGroup attrGroupEntity = attrGroupDao.selectById(relationEntity.getAttrGroupId());
                if (attrGroupEntity != null) {
                    respVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }
        }

        //2、设置分类信息
        Long catelogId = attrEntity.getCatelogId();
        Long[] catelogPath = categoryService.findCatelogPath(catelogId);

        respVo.setCatelogPath(catelogPath);
        Category categoryEntity = categoryService.getById(catelogId);
        if (categoryEntity != null) {
            respVo.setCatelogName(categoryEntity.getName());
        }

        return respVo;
    }
}
