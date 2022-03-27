package com.api.shop.modules.service;

import com.api.shop.common.exception.BadRequestException;
import com.api.shop.modules.entity.Member;
import com.api.shop.modules.form.MemberAddForm;
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
     * @param memberAddForm
     */
    @Transactional
    public Long saveMember(MemberAddForm memberAddForm) {
        // password encoding
        memberAddForm.setPassword(passwordEncoder.encode(memberAddForm.getPassword()));

        // save member
        Member member = modelMapper.map(memberAddForm, Member.class);
        member.setRoleUser();

        Member newMember = memberRepository.save(member);
        return newMember.getIdx();
    }

    /**
     * 회원정보 수정
     * @param memberUpdateForm
     */
    @Transactional
    public void updateMember(MemberUpdateForm memberUpdateForm) {
        Optional<Member> member = memberRepository.findById(memberUpdateForm.getIdx());

        if (member.isEmpty()) {
            throw new BadRequestException("존재하지 않는 회원입니다.");
        }

        // update (Member : 영속성 객체)
        String password = passwordEncoder.encode(memberUpdateForm.getPassword());
        member.get().updatePassword(password);
    }

    /**
     * 회원 단건조회
     * @param idx
     */
    public Member getMember(long idx) {
        Optional<Member> byId = memberRepository.findById(idx);

        if (byId.isEmpty()) {
            throw new BadRequestException("존재하지 않는 회원입니다.");
        }

        return byId.get();
    }
}
