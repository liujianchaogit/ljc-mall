package com.ljc.ware.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljc.common.dto.ware.SkuStock;
import com.ljc.common.exceptions.NoStockException;
import com.ljc.ware.entity.WareOrderTask;
import com.ljc.ware.entity.WareOrderTaskDetail;
import com.ljc.ware.entity.WareSku;
import com.ljc.ware.mapper.WareSkuMapper;
import com.ljc.ware.service.IWareOrderTaskDetailService;
import com.ljc.ware.service.IWareOrderTaskService;
import com.ljc.ware.service.IWareSkuService;
import com.ljc.ware.vo.OrderItemVo;
import com.ljc.ware.vo.WareSkuLockVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper, WareSku> implements IWareSkuService {

    @Autowired
    private IWareOrderTaskService wareOrderTaskService;
    @Autowired
    private IWareOrderTaskDetailService wareOrderTaskDetailService;

    @Override
    public List<SkuStock> getSkuStockList(List<Long> skuIds) {
        return skuIds.stream().map(skuId -> {
            Long count = baseMapper.getSkuStock(skuId);
            return new SkuStock(skuId, count != null && count > 0);
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public boolean orderLockStock(WareSkuLockVo vo) {
        WareOrderTask wareOrderTaskEntity = new WareOrderTask();
        wareOrderTaskEntity.setOrderSn(vo.getOrderSn());
        wareOrderTaskEntity.setCreateTime(LocalDateTime.now());
        wareOrderTaskService.save(wareOrderTaskEntity);


        //1、按照下单的收货地址，找到一个就近仓库，锁定库存
        //2、找到每个商品在哪个仓库都有库存
        List<OrderItemVo> locks = vo.getLocks();

        List<SkuWareHasStock> collect = locks.stream().map((item) -> {
            SkuWareHasStock stock = new SkuWareHasStock();
            Long skuId = item.getSkuId();
            stock.setSkuId(skuId);
            stock.setNum(item.getCount());
            //查询这个商品在哪个仓库有库存
            List<Long> wareIdList = baseMapper.listWareIdHasSkuStock(skuId);
            stock.setWareId(wareIdList);

            return stock;
        }).collect(Collectors.toList());

        //2、锁定库存
        for (SkuWareHasStock hasStock : collect) {
            boolean skuStocked = false;
            Long skuId = hasStock.getSkuId();
            List<Long> wareIds = hasStock.getWareId();

            if (org.springframework.util.StringUtils.isEmpty(wareIds)) {
                //没有任何仓库有这个商品的库存
                throw new NoStockException(skuId+"meiyou");
            }

            //1、如果每一个商品都锁定成功,将当前商品锁定了几件的工作单记录发给MQ
            //2、锁定失败。前面保存的工作单信息都回滚了。发送出去的消息，即使要解锁库存，由于在数据库查不到指定的id，所有就不用解锁
            for (Long wareId : wareIds) {
                //锁定成功就返回1，失败就返回0
                Long count = baseMapper.lockSkuStock(skuId,wareId,hasStock.getNum());
                if (count == 1) {
                    skuStocked = true;
                    WareOrderTaskDetail taskDetailEntity = WareOrderTaskDetail.builder()
                            .skuId(skuId)
                            .skuName("")
                            .skuNum(hasStock.getNum())
                            .taskId(wareOrderTaskEntity.getId())
                            .wareId(wareId)
                            .lockStatus(1)
                            .build();
                    wareOrderTaskDetailService.save(taskDetailEntity);

                    //TODO 告诉MQ库存锁定成功
//                    StockLockedTo lockedTo = new StockLockedTo();
//                    lockedTo.setId(wareOrderTaskEntity.getId());
//                    StockDetailTo detailTo = new StockDetailTo();
//                    BeanUtils.copyProperties(taskDetailEntity,detailTo);
//                    lockedTo.setDetailTo(detailTo);
//                    rabbitTemplate.convertAndSend("stock-event-exchange","stock.locked",lockedTo);
                    break;
                } else {
                    //当前仓库锁失败，重试下一个仓库
                }
            }

            if (!skuStocked) {
                //当前商品所有仓库都没有锁住
                throw new NoStockException(skuId+"meiyou");
            }
        }

        //3、肯定全部都是锁定成功的
        return true;
    }

    @Data
    class SkuWareHasStock {
        private Long skuId;
        private Integer num;
        private List<Long> wareId;
    }
}
