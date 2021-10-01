package com.seohae.java.member.service;

import com.seohae.java.member.dto.entity.Member;
import com.seohae.java.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long addMember(Member member) {
        memberRepository.save(member);
        return member.getUserIdx(); // 항상 값이 있다는 보장이 있음
    }
}
