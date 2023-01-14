package study.querydsl.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.dto.QMemberTeamDto;
import study.querydsl.entity.Member;

import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;

public class MemberRepositoryImpl_support extends QuerydslRepositorySupport implements MemberRepositoryCustom {
    /**
     * 기능을 제공해준다.
     *
     * QuerydslRepositorySupport 에서 EntityManager 가 선언되어있다.
     */
    public MemberRepositoryImpl_support() {
        super(Member.class);
    }

//public class MemberRepositoryImpl implements MemberRepositoryCustom {
//    private final JPAQueryFactory queryFactory;
//
//    public MemberRepositoryImpl(EntityManager em) {
//        this.queryFactory = new JPAQueryFactory(em);
//    }

    @Override
    //회원명, 팀명, 나이(ageGoe, ageLoe)
    public List<MemberTeamDto> search(MemberSearchCondition condition) {
        List<MemberTeamDto> result = from(member)
                .leftJoin(member.team, team)
                .where(usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe()))
                .select(new QMemberTeamDto(
                        member.id,
                        member.username,
                        member.age,
                        team.id,
                        team.name))
                .fetch();

        return result;
    }

    @Override
    public Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable) {
        return null;
    }

    /*
     * [MemberSearchCondition 단점]
     * 1) Querydsl 3.x 버전을 대상으로 만듬
     * 2) Querydsl 4.x에 나온 JPAQueryFactory로 시작할 수 없음
     * 3) select로 시작할 수 없음 (from으로 시작해야함)
     * 4) QueryFactory 를 제공하지 않음
     * 5) 스프링 데이터 Sort 기능이 정상 동작하지 않음
     */
//    @Override
//    public Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable) {
//        JPQLQuery<MemberTeamDto> select = from(member)
//                .leftJoin(member.team, team)
//                .where(usernameEq(condition.getUsername()),
//                        teamNameEq(condition.getTeamName()),
//                        ageGoe(condition.getAgeGoe()),
//                        ageLoe(condition.getAgeLoe()))
//                .select(new QMemberTeamDto(
//                        member.id,
//                        member.username,
//                        member.age,
//                        team.id,
//                        team.name));
//
//        // offset, limit 메서드 호출해준다.
//        // 중간에 코드가 끊기는 느낌
//        JPQLQuery<MemberTeamDto> jpql = getQuerydsl().applyPagination(pageable, select);
//
//        List<MemberTeamDto> result = jpql.fetch();// 페이징 적용된 상태로 쿼리를 실행
//
//        return result;
//    }

    @Override
    public Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable) {
        return null;
    }

    @Override
    public Page<MemberTeamDto> searchPageComplex_PageableExecutionUtils(MemberSearchCondition condition, Pageable pageable) {
        return null;
    }

    private BooleanExpression usernameEq(String username) {
        return isEmpty(username) ? null : member.username.eq(username);
    }

    private BooleanExpression teamNameEq(String teamName) {
        return isEmpty(teamName) ? null : team.name.eq(teamName);
    }

    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe == null ? null : member.age.goe(ageGoe);
    }

    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe == null ? null : member.age.loe(ageLoe);
    }
}