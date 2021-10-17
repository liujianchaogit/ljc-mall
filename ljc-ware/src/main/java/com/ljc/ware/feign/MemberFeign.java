package com.ljc.ware.feign;

import com.ljc.common.api.R;
import com.ljc.common.dto.ware.MemberAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("ljc-member")
public interface MemberFeign {

    @GetMapping("/member/memberreceiveaddress/info/{id}")
    R<MemberAddressVo> info(@PathVariable("id") Long addrId);

}
