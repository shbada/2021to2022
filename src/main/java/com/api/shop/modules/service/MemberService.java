package com.api.shop.modules.service;

import com.api.shop.modules.entity.Member;
import com.api.shop.modules.form.MemberForm;
import com.api.shop.modules.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원등록
     * @param memberForm
     */
    public void saveMember(MemberForm memberForm) {
        // password encoding
        memberForm.setPassword(passwordEncoder.encode(memberForm.getPassword()));

        // save member
        Member member = modelMapper.map(memberForm, Member.class);
        member.setRoleUser();

        memberRepository.save(member);
    }
}
