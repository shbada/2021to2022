package com.login.service;

import com.login.entity.Users;
import com.login.form.LoginForm;
import com.login.repository.UserRepository;
import com.login.security.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
/**
 * UserDetailsService 인터페이스가 구현되어야한다.
 * 또한 SecurityConfig.java 에서 로그인 화면에 대한 url 설정을 해야
 * 아래 인터페이스를 구현한 클래스의 loadUserByUsername 메서드를 찾아간다.
 */
public class AccountService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 로그인
     * @param loginForm
     */
//    public void login(LoginForm loginForm) {
//        Users users = userRepository.findByUserIdAndPassword(
//                loginForm.getUserId(), passwordEncoder.encode(loginForm.getPassword()));
//
//        if (users == null) {
//            throw new IllegalArgumentException(loginForm.getUserId() + "에 해당하는 사용자가 없습니다.");
//        }
//
//        /* spring security */
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken (
//                new UserAccount(users), // UserAccount 설정 (principal)
//                users.getPassword(),
//                List.of(new SimpleGrantedAuthority("ROLE_USER")));
//
//        SecurityContextHolder.getContext().setAuthentication(token);
//    }

    /**
     * 유저 정보를 불러오는 작업 수행
     * @param userId
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        /**
         * userId 는 login.html 에서 id에 해당하는 input 의 id가 username 인데 이게 위 파라미터로 매핑된다.
         */
        Users users = userRepository.findByUserId(userId);

        if (users == null) {
            throw new IllegalArgumentException(userId + "에 해당하는 사용자가 없습니다.");
        }

        return new UserAccount(users);
    }
}
