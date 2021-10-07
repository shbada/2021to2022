package com.seohae.java.service;

import com.seohae.java.common.exceptions.BadRequestException;
import com.seohae.java.dto.MemberDto;
import com.seohae.java.dto.entity.Member;
import com.seohae.java.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 멤버 단건 등록
     * @param member
     * @return
     */
    @Transactional
    public Long addMember(Member member) {
        memberRepository.save(member);
        return member.getUserIdx(); // 항상 값이 있다는 보장이 있음
    }

    /**
     * 멤버 전체 리스트 조회
     * @return
     */
    public List<MemberDto> getMemberList() {
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

        return memberDtoList;
    }

    /**
     * userName 에 해당하는 리스트 조회
     * @param userName
     * @param memberDtoList
     * @return
     */
    public List<MemberDto> getMemberNameFilterList(String userName, List<MemberDto> memberDtoList) {
        /* validation check */
        if (!StringUtils.hasText(userName)) {
            throw new BadRequestException("파라미터 누락: userName");
        }

        /** Stream 연습) filter 사용하여 데이터 필터링 */
        Stream<MemberDto> memberDtoStream = memberDtoList.stream()
                .filter(memberDto -> userName.equals(memberDto.getUserName()));

        return memberDtoStream.collect(Collectors.toList());
    }

    /**
     * userName 으로만 이루어진 리스트 추출
     * @return
     */
    public List<String> getMemberNameMapList(List<MemberDto> memberDtoList) {
        /** Stream 연습) map 사용하여 데이터 재조합 */
        Stream<String> memberDtoStream = memberDtoList.stream()
                .map(MemberDto::getUserName);

        return memberDtoStream.collect(Collectors.toList());
    }

    public MemberDto getMemberFirst(List<MemberDto> memberList) {
        /** Stream 연습) map 사용하여 데이터 재조합 */
        Optional<MemberDto> optionalMemberDto = memberList.stream()
                                                          .findFirst();

        /* TODO Optional Null check */

        return optionalMemberDto.get();
    }
}
