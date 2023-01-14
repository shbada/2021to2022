package main.join;

import jpql.Member;
import jpql.Team;

import javax.persistence.*;
import java.util.List;

public class JpaMain_left {
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

            // as m 필수
            // 수행 순간, select Team 쿼리도 수행된다. 이때 FetchType.LAZY 해야한다. (Member > team 필드)
            TypedQuery<Member> query = em.createQuery("select m from Member as m left join m.team t", Member.class);

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
