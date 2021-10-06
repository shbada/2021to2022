package com.seohae.java;

import com.seohae.java.dto.MemberDto;
import com.seohae.java.dto.entity.Member;
import com.seohae.java.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JavaCommandLineRunner implements CommandLineRunner {
    private final MemberService memberService;

    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 50; i++) {
            boolean flag = false;
            if (i % 2 == 0) {
                flag = true;
            }

            MemberDto memberDto = new MemberDto();
            memberDto.setUserId("seohae" + i);

            if (flag) {
                memberDto.setUserSex("F");
            } else {
                memberDto.setUserSex("M");
            }

            memberDto.setPhone("01012341111");
            memberDto.setUserAge(20 + i);
            memberDto.setTelNo("0299998787");
            memberDto.setUserName("김서해");

            ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); /* 강력하게 */

            Member member = mapper.map(memberDto, Member.class);

            memberService.addMember(member);
        }
    }
}
