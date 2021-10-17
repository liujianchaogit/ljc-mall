package com.ljc.order.feign;

import com.ljc.common.api.R;
import com.ljc.common.dto.ware.MemberAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("ljc-member")
public interface MemberFeign {

    @GetMapping("/member/memberReceiveAddress/{memberId}")
    R<List<MemberAddressVo>> get(@PathVariable("memberId") Long memberId);

}
