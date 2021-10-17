package com.ljc.product.service;

import com.ljc.product.entity.AttrGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljc.product.vo.SpuItemAttrGroupVo;

import java.util.List;

/**
 * <p>
 * 属性分组 服务类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
public interface IAttrGroupService extends IService<AttrGroup> {

    List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId);
}
