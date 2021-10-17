package com.ljc.ware.controller;


import com.ljc.common.dto.ware.FareVo;
import com.ljc.ware.service.IWareInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ware/wareInfo")
public class WareInfoController {

    @Autowired
    private IWareInfoService wareInfoService;

    @GetMapping(value = "/fare")
    public FareVo getFare(@RequestParam("addrId") Long addrId) {
        return wareInfoService.getFare(addrId);
    }
}

