package com.ljc.auth.service.impl;

import com.ljc.auth.feign.AdminFeign;
import com.ljc.auth.feign.MemberFeign;
import com.ljc.auth.security.User;
import com.ljc.common.api.R;
import com.ljc.common.dto.UserDto;
import com.ljc.common.enums.ApiErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private AdminFeign adminFeign;
    @Autowired
    private MemberFeign memberFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String x  = SecurityContextHolder.getContext().getAuthentication().getName();

        R<UserDto> r = memberFeign.loadUserByUsername(username);
        if (r.getErrorCode() != ApiErrorCode.SUCCESS.getCode())
            throw new RuntimeException(r.getErrorMessage());
        if (r.getData()==null)
            throw new UsernameNotFoundException("用户名不存在");
        return new User(r.getData());
    }
}
