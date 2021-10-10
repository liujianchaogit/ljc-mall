package com.ljc.product.controller;


import com.ljc.common.api.R;
import com.ljc.product.service.IAttrService;
import com.ljc.product.vo.AttrRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品属性 前端控制器
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-09
 */
@RestController
@RequestMapping("/product/attr")
public class AttrController {

    @Autowired
    private IAttrService attrService;

    @GetMapping("/info/{attrId}")
    public R<AttrRespVo> info(@PathVariable("attrId") Long attrId) {
        AttrRespVo respVo = attrService.getAttrInfo(attrId);
        return R.ok(respVo);
    }
}

