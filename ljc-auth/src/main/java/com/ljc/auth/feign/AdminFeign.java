package com.ljc.auth.feign;

import com.ljc.common.api.R;
import com.ljc.common.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ljc-admin")
public interface AdminFeign {

    @GetMapping("/user/loadByUsername")
    R<UserDto> loadUserByUsername(@RequestParam("username") String username);

}
