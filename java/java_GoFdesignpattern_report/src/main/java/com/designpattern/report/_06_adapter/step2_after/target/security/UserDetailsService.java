package com.designpattern.report._06_adapter.step2_after.target.security;

public interface UserDetailsService {
    UserDetails loadUser(String username);
}
