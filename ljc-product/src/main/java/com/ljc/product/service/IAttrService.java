package com.ljc.product.service;

import com.ljc.common.dto.product.AttrInfo;
import com.ljc.product.entity.Attr;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
public interface IAttrService extends IService<Attr> {

    AttrInfo info(Long id);
}
