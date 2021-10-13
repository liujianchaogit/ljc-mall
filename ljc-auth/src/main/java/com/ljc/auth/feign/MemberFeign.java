package com.ljc.auth.feign;

import com.ljc.common.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ljc-member")
public interface MemberFeign {

    @GetMapping("/member/member/loadByUsername")
    UserDto loadUserByUsername(@RequestParam("username") String username);

}
