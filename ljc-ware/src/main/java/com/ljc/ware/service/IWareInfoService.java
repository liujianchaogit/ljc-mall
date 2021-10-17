package com.ljc.ware.service;

import com.ljc.common.dto.ware.FareVo;
import com.ljc.ware.entity.WareInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 仓库信息 服务类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-17
 */
public interface IWareInfoService extends IService<WareInfo> {

    FareVo getFare(Long addrId);
}
