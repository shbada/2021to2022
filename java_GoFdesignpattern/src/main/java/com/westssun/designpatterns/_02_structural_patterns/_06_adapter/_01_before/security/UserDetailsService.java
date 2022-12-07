package com.westssun.designpatterns._02_structural_patterns._06_adapter._01_before.security;

/**
 * target
 */
public interface UserDetailsService {

    UserDetails loadUser(String username);

}
