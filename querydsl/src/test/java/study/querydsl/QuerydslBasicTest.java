package study.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberDto;
import study.querydsl.dto.QMemberDto;
import study.querydsl.dto.UserDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.QTeam;
import study.querydsl.entity.Team;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static study.querydsl.entity.QMember.*;
import static study.querydsl.entity.QTeam.team;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {
    @PersistenceContext
    EntityManager em;

    /**
     * JPAQueryFactory를 필드로 제공하면 동시성 문제는 어떻게 될까?
     * 동시성 문제는 JPAQueryFactory를 생성할 때 제공하는 EntityManager(em)에 달려있다.
     * 스프링 프레임워크는 여러 쓰레드에서 동시에 같은 EntityManager에 접근해도,
     * 트랜잭션 마다 별도의 영속성 컨텍스트를 제공하기 때문에, 동시성 문제는 걱정하지 않아도 된다.
     */
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    /**
     * JPQL 사용
     */
    @Test
    public void startJPQL() {
        // member1을 찾아라.
        String qlString =
                "select m from Member m " +
                        "where m.username = :username";

        Member findMember = em.createQuery(qlString, Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    /**
     * Querydsl 사용
     * JPQL: 문자(실행 시점 오류), Querydsl: 코드(컴파일 시점 오류)
     */
    @Test
    public void startQuerydsl() {
        // member1을 찾아라.
        // factory 생성시 entityManager를 넘겨줘야한다.
//        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//        QMember m = new QMember("m"); // 별칭 명시해줘야한다. (구분값)

        // 같은 테이블을 조인해야 하는 경우에만 "m"을 지정해서 사용하자. 그 외에는 startQuerydsl3() 처럼 사용하자.
//        QMember m = new QMember("m"); //별칭 직접 지정
        QMember m = QMember.member; //기본 인스턴스 사용
        // static import도 가능

        Member findMember = queryFactory
                .select(m)
                .from(m)
                .where(m.username.eq("member1"))// 파라미터 바인딩 처리
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQuerydsl3() {
        // member1을 찾아라.
        Member findMember = queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    /*
        member.username.eq("member1") // username = 'member1'
        member.username.ne("member1") //username != 'member1'
        member.username.eq("member1").not() // username != 'member1'
        member.username.isNotNull() //이름이 is not null
        member.age.in(10, 20) // age in (10,20)
        member.age.notIn(10, 20) // age not in (10, 20)
        member.age.between(10,30) //between 10, 30
        member.age.goe(30) // age >= 30
        member.age.gt(30) // age > 30
        member.age.loe(30) // age <= 30
        member.age.lt(30) // age < 30
        member.username.like("member%") //like 검색
        member.username.contains("member") // like ‘%member%’ 검색
        member.username.startsWith("member") //like ‘member%’ 검색
     */
    @Test
    public void search() {
        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1")
                        .and(member.age.eq(10)))
                .fetchOne();
        // 검색 조건은 .and() , . or() 를 메서드 체인으로 연결할 수 있다.

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void searchAndParam() {
        List<Member> result1 = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1"),
                        member.age.eq(10))
                .fetch();
        // where() 에 파라미터로 검색조건을 추가하면 AND 조건이 추가됨
        // -> null 값은 무시

        assertThat(result1.size()).isEqualTo(1);
    }

    /*
        fetch() : 리스트 조회, 데이터 없으면 빈 리스트 반환
        fetchOne() : 단 건 조회
        -> 결과가 없으면 : null
        -> 결과가 둘 이상이면 : com.querydsl.core.NonUniqueResultException
        fetchFirst() : limit(1).fetchOne()
        fetchResults() : 페이징 정보 포함, total count 쿼리 추가 실행
        -> 페이징 쿼리가 복잡해지면 데이터,totalCount 조회 쿼리가 다를 수도 있다.
           성능 때문에 totalCount 쿼리를 좀더 간단하게 조회할 수 있으므로,
           성능이 중요할경우 둘을 따로 조회하자.
        fetchCount() : count 쿼리로 변경해서 count 수 조회
     */
    @Test
    public void resultFetch() {
        // List
        List<Member> fetch = queryFactory
                .selectFrom(member)
                .fetch();

        // 단 건
        Member findMember1 = queryFactory
                .selectFrom(member)
                .fetchOne();

        // 처음 한 건 조회
        Member findMember2 = queryFactory
                .selectFrom(member)
                .fetchFirst();

        // 페이징에서 사용
        // query 가 별도로 수행된다. (select count(member1) ...)
        QueryResults<Member> results = queryFactory
                .selectFrom(member)
                .fetchResults();

        results.getTotal();
        List<Member> content = results.getResults();

        // count 쿼리로 변경
        long count = queryFactory
                .selectFrom(member)
                .fetchCount();
    }

    /**
     * 회원 정렬 순서
     * 1. 회원 나이 내림차순(desc)
     * 2. 회원 이름 올림차순(asc)
     * 단 2에서 회원 이름이 없으면 마지막에 출력(nulls last)
     */
    @Test
    public void sort() {
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();
        // nullsLast(), nullsFirst()

        Member member5 = result.get(0);
        Member member6 = result.get(1);
        Member memberNull = result.get(2);

        assertThat(member5.getUsername()).isEqualTo("member5");
        assertThat(member6.getUsername()).isEqualTo("member6");
        assertThat(memberNull.getUsername()).isNull();
    }

    /**
     * 조회건수 제한
     */
    @Test
    public void paging1() {
        List<Member> result = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1) // 0부터 시작(zero index): 1개를 skip한다.
                .limit(2) // 최대 2건 조회
                .fetch();

        assertThat(result.size()).isEqualTo(2);
    }

    /**
     * 전체 조회 수가 필요
     * count 쿼리가 실행되니 성능상 주의!
     */
    @Test
    public void paging2() {
        // 1) count 쿼리
        // 2) contents 쿼리
        QueryResults<Member> queryResults = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetchResults();

        assertThat(queryResults.getTotal()).isEqualTo(4);
        assertThat(queryResults.getLimit()).isEqualTo(2);
        assertThat(queryResults.getOffset()).isEqualTo(1);
        assertThat(queryResults.getResults().size()).isEqualTo(2);
    }

    /**
     * JPQL
     * select
     * COUNT(m), //회원수
     * SUM(m.age), //나이 합
     * AVG(m.age), //평균 나이
     * MAX(m.age), //최대 나이
     * MIN(m.age) //최소 나이
     * from Member m
     */
    @Test
    public void aggregation() throws Exception {
        List<Tuple> result = queryFactory
                .select(member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.min())
                .from(member)
                .fetch();

        Tuple tuple = result.get(0);
        assertThat(tuple.get(member.count())).isEqualTo(4);
        assertThat(tuple.get(member.age.sum())).isEqualTo(100);
        assertThat(tuple.get(member.age.avg())).isEqualTo(25);
        assertThat(tuple.get(member.age.max())).isEqualTo(40);
        assertThat(tuple.get(member.age.min())).isEqualTo(10);
    }

    /**
     * 팀의 이름과 각 팀의 평균 연령을 구해라.
     */
    @Test
    public void group() throws Exception {
        List<Tuple> result = queryFactory
                .select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
//                .having(item.price.gt(1000))
                .fetch();
        // groupBy , 그룹화된 결과를 제한하려면 having

        // team 이 2개
        Tuple teamA = result.get(0);
        Tuple teamB = result.get(1);

        assertThat(teamA.get(team.name)).isEqualTo("teamA");
        assertThat(teamA.get(member.age.avg())).isEqualTo(15);
        assertThat(teamB.get(team.name)).isEqualTo("teamB");
        assertThat(teamB.get(member.age.avg())).isEqualTo(35);
    }

    /**
     * 팀 A에 소속된 모든 회원
     */
    @Test
    public void join() throws Exception {
        QMember member = QMember.member;
        QTeam team = QTeam.team;

        /*
            join() , innerJoin() : 내부 조인(inner join)
            leftJoin() : left 외부 조인(left outer join)
            rightJoin() : right 외부 조인(right outer join)
         */
        List<Member> result = queryFactory
                .selectFrom(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();
        // join(조인 대상, 별칭으로 사용할 Q타입)

        assertThat(result)
                .extracting("username")
                .containsExactly("member1", "member2");
    }

    /**
     * 세타 조인(연관관계가 없는 필드로 조인)
     * 회원의 이름이 팀 이름과 같은 회원 조회
     */
    @Test
    public void theta_join() throws Exception {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));

        // cross join
        List<Member> result = queryFactory
                .select(member)
                .from(member, team)
                .where(member.username.eq(team.name))
                .fetch();
        // from 절에 여러 엔티티를 선택해서 세타 조인
        // outer join 불가능

        assertThat(result)
                .extracting("username")
                .containsExactly("teamA", "teamB");
    }

    /**
     * 예) 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회
     * JPQL: SELECT m, t FROM Member m LEFT JOIN m.team t on t.name = 'teamA'
     * SQL: SELECT m.*, t.* FROM Member m LEFT JOIN Team t ON m.TEAM_ID=t.id and
            t.name='teamA'
     */
    @Test
    public void join_on_filtering() throws Exception {
        /*
            t=[Member(id=3, username=member1, age=10), Team(id=1, name=teamA)]
            t=[Member(id=4, username=member2, age=20), Team(id=1, name=teamA)]
            t=[Member(id=5, username=member3, age=30), null]
            t=[Member(id=6, username=member4, age=40), null]
         */
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(member.team, team).on(team.name.eq("teamA"))
                .fetch();
//
//        List<Tuple> result = queryFactory
//                .select(member, team)
//                .from(member)
//                .join(member.team, team)
//                .where(team.name.eq("teamA"))
//                .fetch();
        // on 절을 활용해 조인 대상을 필터링 할 때, 외부조인이 아니라 내부조인(inner join)을 사용하면,
        // where 절에서 필터링 하는 것과 기능이 동일하다.
        // 따라서 on 절을 활용한 조인 대상 필터링을 사용할 때, 내부조인 이면 익숙한 where 절로 해결하고,
        // 정말 외부조인이 필요한 경우에만 이 기능을 사용하자.

        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    /**
     * 2. 연관관계 없는 엔티티 외부 조인
     * 예) 회원의 이름과 팀의 이름이 같은 대상 외부 조인
     * JPQL: SELECT m, t FROM Member m LEFT JOIN Team t on m.username = t.name
     * SQL: SELECT m.*, t.* FROM Member m LEFT JOIN Team t ON m.username = t.name
     *
     * 하이버네이트 5.1부터 on 을 사용해서 서로 관계가 없는 필드로 외부 조인하는 기능이 추가
     */
    @Test
    public void join_on_no_relation() throws Exception {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));

        /*
            t=[Member(id=3, username=member1, age=10), null]
            t=[Member(id=4, username=member2, age=20), null]
            t=[Member(id=5, username=member3, age=30), null]
            t=[Member(id=6, username=member4, age=40), null]
            t=[Member(id=7, username=teamA, age=0), Team(id=1, name=teamA)]
            t=[Member(id=8, username=teamB, age=0), Team(id=2, name=teamB)]
         */
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(team).on(member.username.eq(team.name))
                .fetch();

//        일반조인: leftJoin(member.team, team) -> ID를 매칭한다.
//        on조인: from(member).leftJoin(team).on(xxx)

        for (Tuple tuple : result) {
            System.out.println("t=" + tuple);
        }
    }

    @Test
    public void fetchJoinNo() throws Exception {
        em.flush();
        em.clear();

        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        // Member만 조회한다.
        boolean loaded =
                emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());

        assertThat(loaded).as("페치 조인 미적용").isFalse();
    }

    @PersistenceUnit // EntityManager 만드는 팩토리
    EntityManagerFactory emf;

    @Test
    public void fetchJoinUse() throws Exception {
        em.flush();
        em.clear();

        Member findMember = queryFactory
                .selectFrom(member)
                .join(member.team, team).fetchJoin()
                .where(member.username.eq("member1"))
                .fetchOne();
        // join(), leftJoin() 등 조인 기능 뒤에 fetchJoin() 이라고 추가하면 된다.

        boolean loaded =
                emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());

        assertThat(loaded).as("페치 조인 적용").isTrue();
    }

    /**
     * 서브 쿼리 eq 사용
     * com.querydsl.jpa.JPAExpressions 사용
     * @throws Exception
     */
    @Test
    public void subQuery() throws Exception {
        // main 쿼리와 겹치면 안되므로 alias를 따로 지정해야한다.
        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(
                        JPAExpressions
                                .select(memberSub.age.max())
                                .from(memberSub)
                ))
                .fetch();

        assertThat(result).extracting("age")
                .containsExactly(40);
    }

    /**
     * 나이가 평균 나이 이상인 회원
     */
    @Test
    public void subQueryGoe() throws Exception {
        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.goe(
                        JPAExpressions
                                .select(memberSub.age.avg())
                                .from(memberSub)
                ))
                .fetch();

        assertThat(result).extracting("age")
                .containsExactly(30,40);
    }

    /**
     * 서브쿼리 여러 건 처리, in 사용
     */
    @Test
    public void subQueryIn() throws Exception {
        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.in(
                        JPAExpressions
                                .select(memberSub.age)
                                .from(memberSub)
                                .where(memberSub.age.gt(10))
                ))
                .fetch();

        assertThat(result).extracting("age")
                .containsExactly(20, 30, 40);
    }

    /**
     * select 절에 subquery
     */
    @Test
    public void selectSubQuery() {
        QMember memberSub = new QMember("memberSub");

        List<Tuple> fetch = queryFactory
                .select(member.username,
                        JPAExpressions
                                .select(memberSub.age.avg())
                                .from(memberSub)
                ).from(member)
                .fetch();

        for (Tuple tuple : fetch) {
            System.out.println("username = " + tuple.get(member.username));
            System.out.println("age = " +
                    tuple.get(JPAExpressions.select(memberSub.age.avg())
                            .from(memberSub)));
        }

        // static import 활용 가능
        // import static com.querydsl.jpa.JPAExpressions.select;
    }

    /*
        JPA JPQL 서브쿼리의 한계점으로 from 절의 서브쿼리(인라인 뷰)는 지원하지 않는다.
        당연히 Querydsl도 지원하지 않는다.
        하이버네이트 구현체를 사용하면 select 절의 서브쿼리는 지원한다.
        Querydsl도 하이버네이트 구현체를 사용하면 select 절의 서브쿼리를 지원한다.

        [from 절의 서브쿼리 해결방안]
        1. 서브쿼리를 join으로 변경한다. (가능한 상황도 있고, 불가능한 상황도 있다.)
        2. 애플리케이션에서 쿼리를 2번 분리해서 실행한다.
        3. nativeSQL을 사용한다.
     */

    /**
     * 단순한 조건
     */
    @Test
    public void basicCase() {
        List<String> result = queryFactory
                .select(member.age
                        .when(10).then("열살")
                        .when(20).then("스무살")
                        .otherwise("기타"))
                .from(member)
                .fetch();
    }

    /**
     * 복잡한 조건
     */
    @Test
    public void complexCase() {
        List<String> result = queryFactory
                .select(new CaseBuilder()
                        .when(member.age.between(0, 20)).then("0~20살")
                        .when(member.age.between(21, 30)).then("21~30살")
                        .otherwise("기타"))
                .from(member)
                .fetch();
    }

    /**
     * orderBy 절에서 case 사용하기
     */
    @Test
    public void orderByCase() {
        /*
            예를 들어서 다음과 같은 임의의 순서로 회원을 출력하고 싶다면?
            1. 0 ~ 30살이 아닌 회원을 가장 먼저 출력
            2. 0 ~ 20살 회원 출력
            3. 21 ~ 30살 회원 출력

            왠만하면 이런 조건들은 쿼리에서 사용하지 말자.
            실제 조건으로 데이터 필터링은 DB 말고 로직에서 처리하자.
         */
        NumberExpression<Integer> rankPath = new CaseBuilder()
                .when(member.age.between(0, 20)).then(2)
                .when(member.age.between(21, 30)).then(1)
                .otherwise(3);

        List<Tuple> result = queryFactory
                .select(member.username, member.age, rankPath)
                .from(member)
                .orderBy(rankPath.desc())
                .fetch();

        /*
            username = member4 age = 40 rank = 3
            username = member1 age = 10 rank = 2
            username = member2 age = 20 rank = 2
            username = member3 age = 30 rank = 1
         */
        for (Tuple tuple : result) {
            String username = tuple.get(member.username);
            Integer age = tuple.get(member.age);
            Integer rank = tuple.get(rankPath);

            System.out.println("username = " + username + " age = " + age + " rank = "
                    + rank);
        }
    }

    @Test
    public void concat1() {
        // 상수 "A"를 무조건 조회한다.
        Tuple result = queryFactory
                .select(member.username, Expressions.constant("A"))
                .from(member)
                .fetchFirst();

        System.out.println(result.get(member.username)); // member1
        System.out.println(Expressions.constant("A")); // A
    }

    @Test
    public void concat() {
        // {username}_{age}
        List<String> result = queryFactory
                .select(member.username.concat("_").concat(member.age.stringValue()))
                .from(member)
                .where(member.username.eq("member1"))
                .fetch();
        // 문자가 아닌 다른 타입들은 stringValue() 로 문자로 변환할 수 있다.
        // 이 방법은 ENUM을 처리할 때도 자주 사용한다.

        for (String s : result) {
            System.out.println(s);
        }
    }

    /**
     * 프로젝션: select 대상 지정
     * 프로젝션 대상이 하나면 타입을 명확하게 지정할 수 있음
     */
    @Test
    public void simpleProjection() {
        List<String> result = queryFactory
                .select(member.username)
                .from(member)
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    /**
     * 프로젝션 대상이 둘 이상이면 튜플이나 DTO로 조회
     */
    @Test
    public void tupleProjection() {
        List<Tuple> result = queryFactory
                .select(member.username, member.age)
                .from(member)
                .fetch();

        /* 반환은 DTO로 변환해서 반환하도록 하자. */
        for (Tuple tuple : result) {
            String username = tuple.get(member.username);
            Integer age = tuple.get(member.age);
            System.out.println("username=" + username);
            System.out.println("age=" + age);
        }
    }

    /**
     * JQPL 방식
     * 생성자 방식만 지원함
     */
    @Test
    public void findDtoByJQPL() {
        List<MemberDto> result = em.createQuery(
                        "select new study.querydsl.dto.MemberDto(m.username, m.age) " +
                                "from Member m", MemberDto.class)
                .getResultList();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto : " + memberDto);
        }
    }


    /**
     * Querydsl 빈 생성(Bean population)
     * 1) 프로퍼티 접근 - Setter
     */
    @Test
    public void findDtoBySetter() {
        // 기본생성자가 존재해야한다.
        // MemberDto 생성 후 각 값을 셋팅하기 때문
        List<MemberDto> result = queryFactory
                .select(Projections.bean(MemberDto.class, member.username, member.age))
                .from(member)
                .fetch();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto : " + memberDto);
        }
    }

    /**
     * Querydsl 빈 생성(Bean population)
     * 2) 필드 직접 접근
     */
    @Test
    public void findDtoByField() {
        // 필드에 값을 꽂는다. (getter, setter 호출이 아님)
        List<MemberDto> result = queryFactory
                .select(Projections.fields(MemberDto.class, member.username, member.age))
                .from(member)
                .fetch();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto : " + memberDto);
        }
    }

    /**
     * Querydsl 빈 생성(Bean population)
     * 프로퍼티나, 필드 접근 생성 방식에서 이름이 다를 때 해결 방안
     */
    @Test
    public void findUserDtoByField() {
        QMember memberSub = new QMember("memberSub");

        List<UserDto> fetch = queryFactory
                .select(Projections.fields(UserDto.class,
                                member.username.as("name"),
//                                ExpressionUtils.as(member.username, "name"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(memberSub.age.max())
                                                .from(memberSub), "age")
                        )
                ).from(member)
                .fetch();

        for (UserDto userDto : fetch) {
            System.out.println("userDto : " + userDto);
        }
    }

    /**
     * Querydsl 빈 생성(Bean population)
     * 3) 생성자 사용
     * 런타임 단계에서 에러 체크 가능
     */
    @Test
    public void findDtoByConstructor() {
        /* 생성자 매개변수와 타입이 동일해야한다. */
        List<MemberDto> result = queryFactory
                .select(Projections.constructor(MemberDto.class, member.username, member.age))
                .from(member)
                .fetch();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto : " + memberDto);
        }
    }

    /**
     * @QueryProjection 활용
     * 장점 : 컴파일러로 타입을 체크
     * 단점 : DTO에 QueryDSL 어노테이션을 유지해야 하는 점과 DTO까지 Q 파일을 생성해야한다.
     */
    @Test
    public void findDtoByQueryProjection() {
        List<MemberDto> result = queryFactory
                .select(new QMemberDto(member.username, member.age))
                .from(member)
                .fetch();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto : " + memberDto);
        }
    }

    /**
     * 중복제거
     */
    @Test
    public void distinct() {
        List<String> result = queryFactory
                .select(member.username).distinct()
                .from(member)
                .fetch();

        for (String s : result) {
            System.out.println("s : " + s);
        }
    }

    /**
     * 동적쿼리
     * 1) BooleanBuilder
     */
    @Test
    public void dynamicQuery_BooleanBuilder() {
        String usernameParameter = "member1";
        Integer ageParam = 10;

        List<Member> result = searchMember1(usernameParameter, ageParam);
        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember1(String usernameCond, Integer ageCond) {
        BooleanBuilder builder = new BooleanBuilder();

        if (usernameCond != null) {
            builder.and(member.username.eq(usernameCond));
        }

        if (ageCond != null) {
            builder.and(member.age.eq(ageCond));
        }

        return queryFactory
                .selectFrom(member)
                .where(builder)
                .fetch();
    }

    /**
     * 동적쿼라
     * 2) Where 다중 파라미터 사용
     */
    @Test
    public void 동적쿼리_WhereParam() throws Exception {
        String usernameParam = "member1";
        Integer ageParam = 10;

        List<Member> result = searchMember2(usernameParam, ageParam);

        Assertions.assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember2(String usernameCond, Integer ageCond) {
        return queryFactory
                .selectFrom(member)
                .where(usernameEq(usernameCond), ageEq(ageCond))
//                .where(allEq(usernameCond,ageCond))
                .fetch();
    }

    /* where 조건에 null 값은 무시된다. */
    private BooleanExpression usernameEq(String usernameCond) {
        return usernameCond != null ? member.username.eq(usernameCond) : null;
    }

    private BooleanExpression ageEq(Integer ageCond) {
        return ageCond != null ? member.age.eq(ageCond) : null;
    }

    /* 조합 가능 */
    private BooleanExpression allEq(String usernameCond, Integer ageCond) {
        return usernameEq(usernameCond).and(ageEq(ageCond));
    }

    /**
     * 쿼리 한번으로 대량 데이터 수정
     */
    @Test
    public void bulkUpdate() {
        long count = queryFactory
                .update(member)
                .set(member.username, "비회원")
                .where(member.age.lt(28))
                .execute();

        /* SQL이 실행되는데, 이때 영속성 컨텍스트와 다른 데이터인데,
           우선순위가 영속성 컨텍스트가 높기 때문에 이전 데이터가 유지되어 문제가 생긴다.
           쿼리는 분명 정상적으로 나갔는데, 영속성 컨텍스트에 존재한다면 영속성 컨텍스트를 우선적으로 가져온다.
           */
        List<Member> list = queryFactory
                .selectFrom(member)
                .fetch();

        /* 영속성 컨텍스트 초기화 */
        em.flush();
        em.clear();

        List<Member> list2 = queryFactory
                .selectFrom(member)
                .fetch();
    }

    /**
     * 쿼리 한번으로 대량 데이터 수정
     */
    @Test
    public void bulkAdd() {
        long addUpdateCount = queryFactory
                .update(member)
                .set(member.age, member.age.add(1))
//                .set(member.age, member.age.add(-1))
                .execute();
    }

    /**
     * 쿼리 한번으로 대량 데이터 삭제
     */
    @Test
    public void bulkDelete() {
        long count = queryFactory
                .delete(member)
                .where(member.age.gt(18))
                .execute();
    }

    /**
     * JPQL 배치와 마찬가지로, 영속성 컨텍스트에 있는 엔티티를 무시하고 실행되기 때문에
     * 배치 쿼리를 실행하고 나면 영속성 컨텍스트를 초기화 하는 것이 안전하다.
     *
     * 영속성 컨텍스트와 DB 데이터가 다르게되기 때문
     */

    /**
     * Sql Function 사용
     * -> SQL function은 JPA와 같이 Dialect에 등록된 내용만 호출할 수 있다.
     *
     * 예를들어 H2Dialect 안에 function으로 등록되어있는 함수 사용이 가능하다.
     * 만약 직접 등록이 필요하다면,
     * Dialect 상속한 파일을 직접 생성해서 yml에 등록해서 사용해야한다.
     */
    @Test
    public void sqlFunction() {
        String result = queryFactory
                .select(Expressions.stringTemplate("function('replace', {0}, {1}, {2})",
                        member.username, "member", "M"))
                .from(member)
                .fetchFirst();
    }

    /**
     * 소문자로 변경
     */
    @Test
    public void sqlFunction2() {
        queryFactory
                .select(member.username)
                .from(member)
                .where(member.username.eq(Expressions.stringTemplate("function('lower', {0})",
                        member.username)));

        // lower 같은 ansi 표준 함수들은 querydsl이 상당부분 내장하고 있다. 따라서 다음과 같이 처리해도 결과는 같다.
        queryFactory
                .select(member.username)
                .from(member)
                .where(member.username.eq(member.username.lower()));
    }
}
