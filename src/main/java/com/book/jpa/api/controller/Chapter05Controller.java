package com.book.jpa.api.controller;

import com.book.jpa.api.repository.MemberRepository;
import com.book.jpa.api.repository.TeamRepository;
import com.book.jpa.api.service.Chapter05Service;
import com.book.jpa.chapter05.Member;
import com.book.jpa.chapter05.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        // team1.setMembers(List.of(member1, member2));

        return "ok";
    }

    @PutMapping("/member/save")
    public String updateMember() {
        // 팀1 저장
        Team team1 = new Team();
        team1.setName("팀1");
        teamRepository.save(team1);

        // 회원1 저장
        Member member1 = new Member();
        member1.setUsername("회원1");
        member1.setTeam(team1); // 연관관계 설정
        memberRepository.save(member1);

        /* 수정 */

        // 팀2 저장
        Team team2 = new Team();
        team2.setName("팀2");
        teamRepository.save(team2);

        Member getMember = memberRepository.getById(member1.getId());

        // getMember 는 영속상태
        // 그러므로 save 없이 set 메서드 호출로 update 쿼리가 발생하여 트랜잭션 커밋할때 플러시가 발생하면서
        // 변경 감지 기능이 작동한다.
        /*
        update
            member
        set
            team_id=?,
            name=?
        where
            id=?
         */
        getMember.setTeam(team2); // 변경
        // getMember.setTeam(null); // 연관관계 삭제
        // teamRepository.delete(team2); // team 삭제하려면 member 가 먼저 삭제되어야한다. (외래 키 제약조건)

        return "ok";
    }

    /**
     * 양방향 연관관계 저장
     * @return
     */
    @PutMapping("/team/save")
    public String saveMemberTeam() {
        // 팀1 저장
        Team team1 = new Team();
        team1.setName("팀1");
        teamRepository.save(team1);

        // 회원1 저장
        Member member1 = new Member();
        member1.setUsername("회원1");
        /**
         * 연관관계 주인은 Member 다.
         * setTeam()을 하지 않으면 Member 의 team_ID는 null이다.
         * 연관관계의 주인만이 외래 키의 값을 변경할 수 있다.
         */
        member1.setTeam(team1); // 연관관계 설정
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setUsername("회원2");
        member2.setTeam(team1); // 연관관계 설정
        memberRepository.save(member2);

        /* 무시) 연관관계의 주인이 team 이 아니므로 의미없다. */
        // 그래도 넣어줘야한다.
        // 테스트에서 team1.getMembers(); 를 출력했을때 아래 코드가 없으면 0 인데, 기대한 값은 2일 것이다.
        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        return "ok";
    }
}
