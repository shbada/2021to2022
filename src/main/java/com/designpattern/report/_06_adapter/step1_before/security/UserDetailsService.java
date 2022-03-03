package com.designpattern.report._06_adapter.step1_before.security;

/**
 * target
 */
public interface UserDetailsService {
    UserDetails loadUser(String username);
}
