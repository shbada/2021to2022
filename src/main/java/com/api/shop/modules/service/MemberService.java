package com.api.shop.modules.service;

import com.api.shop.common.exception.BadRequestException;
import com.api.shop.modules.entity.Member;
import com.api.shop.modules.form.MemberForm;
import com.api.shop.modules.form.MemberUpdateForm;
import com.api.shop.modules.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    @Transactional
    public Long saveMember(MemberForm memberForm) {
        // password encoding
        memberForm.setPassword(passwordEncoder.encode(memberForm.getPassword()));

        // save member
        Member member = modelMapper.map(memberForm, Member.class);
        member.setRoleUser();

        Member newMember = memberRepository.save(member);
        return newMember.getIdx();
    }

    /**
     * 회원정보 수정
     * @param memberUpdateForm
     */
    public void updateMember(MemberUpdateForm memberUpdateForm) {
        Optional<Member> member = memberRepository.findById(memberUpdateForm.getIdx());

        if (member.isEmpty()) {
            throw new BadRequestException("존재하지 않는 회원입니다.");
        }

        // update (Member : 영속성 객체)
        String password = passwordEncoder.encode(memberUpdateForm.getPassword());
        member.get().updatePassword(password);
    }
}
