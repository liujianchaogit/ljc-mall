package com.ljc.auth.server.service.impl;

import com.ljc.auth.server.feign.AdminFeign;
import com.ljc.auth.server.feign.MemberFeign;
import com.ljc.auth.server.security.User;
import com.ljc.common.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService {

    private final AdminFeign adminFeign;
    private final MemberFeign memberFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto;
        if ("admin".equals(SecurityContextHolder.getContext().getAuthentication().getName()))
            userDto = adminFeign.login(username);
        else
            userDto = memberFeign.login(username);
        if (userDto == null)
            throw new UsernameNotFoundException("用户名不存在");
        return new User(userDto);
    }

}
