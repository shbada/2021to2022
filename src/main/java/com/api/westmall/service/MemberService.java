package com.api.westmall.service;

import com.api.westmall.entity.Member;
import com.api.westmall.form.MemberForm;
import com.api.westmall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    /**
     * 회원 저장
     * @param memberForm
     */
    public void saveUser(MemberForm memberForm) {
        Member member = modelMapper.map(memberForm, Member.class);

        memberRepository.save(member);
    }
}
