package com.login.security;

import com.login.entity.Users;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserAccount extends User {

    private Users users;

    /**
     * springSecurity userAccount
     * @param users
     */
    public UserAccount(Users users) {
        super(users.getUserId(), users.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.users = users;
    }
}
