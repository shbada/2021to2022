package main._페치조인;

import jpql.Member;
import jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain_collection {
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

            // 페치 조인 (항상 우선적으로)
            // 쿼리가 한번에 나간다.
            String fetchJpql = "select t from Team t join fetch t.members";
            List<Team> fetchResult = em.createQuery(fetchJpql, Team.class).getResultList();

            for (Team team : fetchResult) {
                // 한방 쿼리로 조회됬으므로 모두 진짜 객체다.

                // 몇개의 row일지 (회원이 몇개로 매핑되어있을지)는 실행 전까진 모른다.
                // teamA 회원1
                // teamA 회원2

                // 중복 제거는?
                // DISTINCT
                   // SQL에서는 완전한 데이터가 동일해야 중복 제거가 되는데, 1개라도 다르므로 SQL 결과에서 중복 제거에 실패
                   // -> String fetchJpql = "select distinct t from Team t join fetch t.members";
                // 근데 JPA 에서는 엔티티 자체에서 중복을 제거한다. (같은 식별자를 가진 Team 엔티티 제거)
                   // 같은 Team 정보이므로 중복 제거된다.
                   // team row 1개 안에 members(List) 의 2개 데이터
                System.out.println("team = " + team.getName() + ", " + team.getMembers().size());
            }

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }
}
