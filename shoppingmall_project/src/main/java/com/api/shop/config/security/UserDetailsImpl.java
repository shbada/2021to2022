package com.api.shop.config.security;

import com.api.shop.modules.entity.Member;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role role = member.getRole();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getMemberName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
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
