package com.api.plan.config.auth;

import com.api.plan.api.entity.Member;
import com.api.plan.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService : 진입");
        Member member = memberRepository.findByMemberId(memberId);

        return new PrincipalDetails(member);
    }
}
