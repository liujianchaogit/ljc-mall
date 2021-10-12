package com.ljc.auth.security;

import com.ljc.common.dto.UserDto;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class User implements UserDetails {

    private final Long userId;
    private final String username;
    private final String password;
    private final Integer locked;
    private final List<GrantedAuthority> authorities;

    public User(UserDto userDto) {
        this.userId = userDto.getId();
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.locked = 0;
        this.authorities = AuthorityUtils.createAuthorityList(userDto.getRoles().toArray(new String[0]));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return "{bcrypt}" + this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.locked == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
