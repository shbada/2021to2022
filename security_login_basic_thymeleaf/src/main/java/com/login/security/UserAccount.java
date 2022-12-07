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
     * loadUserByUsername 가 수행되면서 아래 생성자가 실행되고 리턴됨.
     * @param users
     */
    public UserAccount(Users users) {
        // String username, String password, Collection<? extends GrantedAuthority> authorities
        super(users.getUserId(), users.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.users = users;
    }
}
