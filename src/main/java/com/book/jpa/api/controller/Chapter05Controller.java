package com.book.jpa.api.controller;

import com.book.jpa.api.repository.MemberRepository;
import com.book.jpa.api.repository.TeamRepository;
import com.book.jpa.api.service.Chapter05Service;
import com.book.jpa.chapter05.Member;
import com.book.jpa.chapter05.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chapter05")
public class Chapter05Controller {
    private final Chapter05Service chapter05Service;
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @GetMapping("/test")
    public String getMember() {
        return "ok";
    }

    @PostMapping("/member/save")
    public String saveMember() {
        // 팀1 저장
        Team team1 = new Team();
        team1.setName("팀1");
        teamRepository.save(team1);

        // 회원1 저장
        Member member1 = new Member();
        member1.setUsername("회원1");
        member1.setTeam(team1); // 연관관계 설정
        memberRepository.save(member1);

        // 회원2 저장
        Member member2 = new Member();
        member2.setUsername("회원2");
        member2.setTeam(team1); // 연관관계 설정
        memberRepository.save(member2);

        return "ok";
    }
}
