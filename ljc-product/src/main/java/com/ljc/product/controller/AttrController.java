package com.ljc.product.controller;

import com.ljc.common.annotation.NoR;
import com.ljc.common.dto.product.AttrInfo;
import com.ljc.product.service.IAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product/attr")
public class AttrController {

    @Autowired
    private IAttrService attrService;

    @GetMapping("/info/{id}")
    @NoR
    public AttrInfo info(@PathVariable("id") Long id) {
        return attrService.info(id);
    }

}

