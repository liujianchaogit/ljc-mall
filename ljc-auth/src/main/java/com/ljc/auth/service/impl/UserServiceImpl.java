package com.ljc.auth.service.impl;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new User(
                "admin",
                "{bcrypt}$2a$10$7Xdedq8Z6WVwM7Jq9Nql7.FTZM6JBLt43x7V6WALjH7/yIB5t1A9u",
                AuthorityUtils.createAuthorityList("fuck", "fuck2"));
    }
}
