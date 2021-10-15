package com.ljc.auth.server.feign;

import com.ljc.common.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ljc-admin")
public interface AdminFeign {

    @GetMapping("/user/login")
    UserDto login(@RequestParam("username") String username);

}
