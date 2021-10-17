package com.ljc.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljc.product.entity.SkuImages;
import com.ljc.product.entity.SkuInfo;
import com.ljc.product.entity.SpuInfoDesc;
import com.ljc.product.mapper.SkuInfoMapper;
import com.ljc.product.service.*;
import com.ljc.product.vo.SkuItemSaleAttrVo;
import com.ljc.product.vo.SkuItemVo;
import com.ljc.product.vo.SpuItemAttrGroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements ISkuInfoService {

    @Autowired
    private ExecutorService executorService;
    @Autowired
    private ISkuSaleAttrValueService skuSaleAttrValueService;
    @Autowired
    private ISpuInfoDescService spuInfoDescService;
    @Autowired
    private IAttrGroupService attrGroupService;
    @Autowired
    private ISkuImagesService skuImagesService;

    @Override
    public SkuItemVo detail(Long skuId) throws ExecutionException, InterruptedException {
        SkuItemVo skuItemVo = new SkuItemVo();

        CompletableFuture<SkuInfo> infoFuture = CompletableFuture.supplyAsync(() -> {
            //1、sku基本信息的获取  pms_sku_info
            SkuInfo info = getById(skuId);
            skuItemVo.setInfo(info);
            return info;
        }, executorService);

        CompletableFuture<Void> saleAttrFuture = infoFuture.thenAcceptAsync((res) -> {
            //3、获取spu的销售属性组合
            List<SkuItemSaleAttrVo> saleAttrVos = skuSaleAttrValueService.getSaleAttrBySpuId(res.getSpuId());
            skuItemVo.setSaleAttr(saleAttrVos);
        }, executorService);


        CompletableFuture<Void> descFuture = infoFuture.thenAcceptAsync((res) -> {
            //4、获取spu的介绍    pms_spu_info_desc
            SpuInfoDesc spuInfoDescEntity = spuInfoDescService.getById(res.getSpuId());
            skuItemVo.setDesc(spuInfoDescEntity);
        }, executorService);


        CompletableFuture<Void> baseAttrFuture = infoFuture.thenAcceptAsync((res) -> {
            //5、获取spu的规格参数信息
            List<SpuItemAttrGroupVo> attrGroupVos = attrGroupService.getAttrGroupWithAttrsBySpuId(res.getSpuId(), res.getCatalogId());
            skuItemVo.setGroupAttrs(attrGroupVos);
        }, executorService);

        //2、sku的图片信息    pms_sku_images
        CompletableFuture<Void> imageFuture = CompletableFuture.runAsync(() -> {
            List<SkuImages> imagesEntities = skuImagesService.getImagesBySkuId(skuId);
            skuItemVo.setImages(imagesEntities);
        }, executorService);

        CompletableFuture<Void> seckillFuture = CompletableFuture.runAsync(() -> {
            //3、远程调用查询当前sku是否参与秒杀优惠活动
//            R skuSeckilInfo = seckillFeignService.getSkuSeckilInfo(skuId);
//            SeckillSkuVo seckilInfoData = new SeckillSkuVo();
//            skuItemVo.setSeckillSkuVo(seckilInfoData);
//            if (seckilInfoData != null) {
//                long currentTime = System.currentTimeMillis();
//                if (currentTime > seckilInfoData.getEndTime()) {
//                    skuItemVo.setSeckillSkuVo(null);
//                }
//            }
        }, executorService);


        //等到所有任务都完成
        CompletableFuture.allOf(saleAttrFuture, descFuture, baseAttrFuture, imageFuture, seckillFuture).get();

        return skuItemVo;
    }
}
