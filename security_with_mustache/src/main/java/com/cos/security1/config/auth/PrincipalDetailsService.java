package com.cos.security1.config.auth;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 시큐리티 설정에서 .loginProcessingUrl("/loginProc")을 걸었으므로
 * /loginProc 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는
 * loadUserByUsername 함수가 실행된다. (규칙)
 */
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * securitySession = Authentication 타입만 가능 (=UserDetails)
     * new PrincipalDetails(user); 을 리턴하면 UserDetails 타입 리턴되는 것
     * 리턴된 UserDetails 는Authentication 내부에 쏙 들어간다.
     * 시큐리티 세션 내부에 Authentication 이 들어간다.
     *
     * security session (내부 Authentication (내부 UserDetails))
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            return null;
        }
        return new PrincipalDetails(user);
    }

}

