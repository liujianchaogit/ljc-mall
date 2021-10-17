package com.ljc.ware.service.impl;

import com.ljc.ware.entity.Purchase;
import com.ljc.ware.mapper.PurchaseMapper;
import com.ljc.ware.service.IPurchaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 采购信息 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-17
 */
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase> implements IPurchaseService {

}
