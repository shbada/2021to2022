package com.westssun.designpatterns._02_structural_patterns._06_adapter._02_after.security;

/**
 * client
 */
public class LoginHandler {
    // target interface
    UserDetailsService userDetailsService;

    public LoginHandler(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String login(String username, String password) {
        // UserDetails : target interface
        UserDetails userDetails = userDetailsService.loadUser(username);

        if (userDetails.getPassword().equals(password)) {
            return userDetails.getUsername();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
