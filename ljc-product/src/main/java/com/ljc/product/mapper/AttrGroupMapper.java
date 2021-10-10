package com.ljc.product.mapper;

import com.ljc.product.entity.AttrGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljc.product.vo.SpuItemAttrGroupVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 属性分组 Mapper 接口
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
public interface AttrGroupMapper extends BaseMapper<AttrGroup> {

    List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(@Param("spuId") Long spuId, @Param("catalogId") Long catalogId);

}
