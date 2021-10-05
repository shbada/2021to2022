package com.seohae.java.member.service;

import com.seohae.java.common.exceptions.BadRequestException;
import com.seohae.java.member.dto.MemberDto;
import com.seohae.java.member.dto.entity.Member;
import com.seohae.java.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<MemberDto> getMemberNameList(String userName) {
        /* validation check */
        if (!StringUtils.hasText(userName)) {
            throw new BadRequestException("파라미터 누락: userName");
        }

        /* member List select */
        Iterable<Member> members = memberRepository.findAll();

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); /* 강력하게 */

        List<MemberDto> memberDtoList = new ArrayList<>();

        /* member entity -> dto */
        for (Member member : members) {
            MemberDto memberDto = mapper.map(member, MemberDto.class);
            memberDtoList.add(memberDto);
        }

        /** Stream 연습) filter 사용하여 데이터 필터링 */
        Stream<MemberDto> memberDtoStream = memberDtoList.stream()
                .filter(memberDto -> userName.equals(memberDto.getUserName()));

        return memberDtoStream.collect(Collectors.toList());
    }
}
