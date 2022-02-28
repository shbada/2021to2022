package main.join;

import jpql.Member;
import jpql.Team;

import javax.persistence.*;
import java.util.List;

public class JpaMain_joinFiltering {
    public static void main(String[] args) {
        // hello : persistence.xml 의 name 값
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("kimseohae");
            member.setAge(10);

            Team team = new Team();
            team.setName("team1");
            team.setName("team");

            member.setTeam(team);

            em.persist(team);
            em.persist(member);

            em.flush();
            em.clear();

            /**
             * on : 연관관계 없는 엔티티도 외부 조인을 할 수 있다. (하이버네이트5.1 부터 제공)
             *      = 회원의 이름과 팀의 이름이 같은 대상 외부 조인
             *      (SELECT m.*, t.* FROM Member m LEFT JOIN Team t ON m.username = t.name)
             *    : 조인 대상 필터링
                    = 회원과 팀을 조인하면서, 팀 이름이 A인 팀만 조인
                    (SELECT m.*, t.* FROM Member m LEFT JOIN Team t ON m.TEAM_ID = t.id and t.name = 'A')
             */
            // as m 필수
            // cross join 
            TypedQuery<Member> query = em.createQuery("select m from Member m left join m.team t on t.name = 'teamA'", Member.class);

            List<Member> resultList = query.getResultList();
            System.out.println("result.size = " + resultList.size());

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }
}
