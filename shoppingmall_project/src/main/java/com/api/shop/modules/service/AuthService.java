package com.api.shop.modules.service;

import com.api.shop.common.exception.BadRequestException;
import com.api.shop.config.security.UserDetailsImpl;
import com.api.shop.modules.entity.Member;
import com.api.shop.modules.form.LoginForm;
import com.api.shop.modules.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    /**
     * 로그인
     * @param loginForm
     */
    public String login(LoginForm loginForm) {
        /* login validation add */
        Optional<Member> byMemberName = memberRepository.findByMemberName(loginForm.getMemberName());
        if (byMemberName.isEmpty()) {
            throw new BadRequestException("MemberName, Password 를 확인해주세요.");
        } else {
            if (!passwordEncoder.matches(loginForm.getPassword(), byMemberName.get().getPassword())) {
                throw new BadRequestException("MemberName, Password 를 확인해주세요.");
            }
        }

        /* login execute */
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getMemberName(), loginForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();

        return principal.getUsername();
    }
}
