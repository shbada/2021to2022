package main._페치조인;

import jpql.Member;
import jpql.Team;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // hello : persistence.xml 의 name 값
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        tx.begin();

        try {
            Team teamA = new Team();
            teamA.setName("tesmA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("tesmA");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("test1");
            member1.setTeam(teamA);
            member1.setAge(10);

            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("test2");
            member2.setTeam(teamA);
            member2.setAge(20);

            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("test3");
            member3.setTeam(teamB);
            member3.setAge(30);

            em.persist(member3);


            // TEAM LAZY (지연로딩이므로 프록시로 들어온다)
            String jpql = "select m from Member m";
            List<Member> result = em.createQuery(jpql, Member.class).getResultList();

            /**
             * 쿼리가 총 3번 날라감..
             * 1) 회원1, 팀A (SQL)
             * 2) 회원2, 팀A (1차캐시)
             * 3) 회원3, 팀B (SQL)
             * 회원이 100명 조회되면... -> N + 1 쿼리가 날라간다.
             * -> 이건 지연로딩이든, 즉시로딩이든 발생함. 페치 조인으로 해결해야한다.
             */
            for (Member member : result) {
                // team.getName() 시점에 team을 조회한다. (getTeam은 프록시였음)
                System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
            }

            // 페치 조인 (항상 우선적으로)
            // 쿼리가 한번에 나간다.
            String fetchJpql = "select m from Member m join fetch m.team";
            List<Member> fetchResult = em.createQuery(fetchJpql, Member.class).getResultList();

            for (Member member : fetchResult) {
                // 한방 쿼리로 조회됬으므로 모두 진짜 객체다.
                System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }
}
