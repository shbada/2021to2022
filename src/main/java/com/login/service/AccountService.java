package com.login.service;

import com.login.entity.Users;
import com.login.form.LoginForm;
import com.login.repository.UserRepository;
import com.login.security.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 로그인
     * @param loginForm
     */
    public void login(LoginForm loginForm) {

    }
}
