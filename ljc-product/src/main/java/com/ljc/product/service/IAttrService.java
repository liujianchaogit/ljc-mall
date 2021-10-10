package com.ljc.product.service;

import com.ljc.product.entity.Attr;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljc.product.vo.AttrRespVo;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
public interface IAttrService extends IService<Attr> {

    AttrRespVo getAttrInfo(Long attrId);
}
