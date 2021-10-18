package com.ljc.ware.mapper;

import com.ljc.ware.entity.WareSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品库存 Mapper 接口
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-17
 */
public interface WareSkuMapper extends BaseMapper<WareSku> {

    Long getSkuStock(long skuId);

    List<Long> listWareIdHasSkuStock(Long skuId);

    Long lockSkuStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("num") Integer num);
}
