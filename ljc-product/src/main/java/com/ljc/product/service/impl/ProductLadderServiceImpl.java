package com.ljc.product.service.impl;

import com.ljc.product.entity.ProductLadder;
import com.ljc.product.mapper.ProductLadderMapper;
import com.ljc.product.service.IProductLadderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品阶梯价格表(只针对同商品) 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-13
 */
@Service
public class ProductLadderServiceImpl extends ServiceImpl<ProductLadderMapper, ProductLadder> implements IProductLadderService {

}
