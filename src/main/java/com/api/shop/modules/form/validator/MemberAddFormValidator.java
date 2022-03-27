package com.api.shop.modules.form.validator;

import com.api.shop.common.exception.BadRequestException;
import com.api.shop.modules.entity.Member;
import com.api.shop.modules.form.MemberAddForm;
import com.api.shop.modules.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberAddFormValidator implements Validator {
    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(MemberAddForm.class);
    }

    /**
     * Account Validation
     * @param object
     * @param errors
     */
    @Override
    public void validate(Object object, Errors errors) {
        MemberAddForm memberAddForm = (MemberAddForm) object;

        Optional<Member> byMemberName = memberRepository.findByMemberName(memberAddForm.getMemberName());

        if (!byMemberName.isEmpty()) {
            throw new BadRequestException("이미 등록된 회원이름 입니다.");
        }
    }
}
