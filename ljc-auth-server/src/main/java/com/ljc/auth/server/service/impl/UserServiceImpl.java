package com.ljc.auth.server.service.impl;

import com.ljc.auth.server.feign.AdminFeign;
import com.ljc.auth.server.feign.MemberFeign;
import com.ljc.auth.server.security.UserDetailsImpl;
import com.ljc.common.api.R;
import com.ljc.common.dto.auth.User;
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
        R<User> r;
        if ("admin".equals(SecurityContextHolder.getContext().getAuthentication().getName()))
            r = adminFeign.login(username);
        else
            r = memberFeign.login(username);
        if (!r.isSuccess())
            throw new RuntimeException(r.getErrorMessage());
        if (r.getData() == null)
            throw new UsernameNotFoundException("用户名不存在");
        return new UserDetailsImpl(r.getData());
    }

}
