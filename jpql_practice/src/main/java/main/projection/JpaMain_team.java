package main.projection;

import jpql.Member;
import jpql.Team;

import javax.persistence.*;
import java.util.List;

/**
 * 프로젝션 : SELECT 절에 조회할 대상을 지정하는것
 * 프로젝션 대상 : 엔티티, 임베디드 타입, 스칼라 타입(숫자, 문자 등 기본 데이터 타입)
 */
public class JpaMain_team {
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

            em.persist(member);

            // as m 필수
            // m.team 조인시 team 과 join 수행
            TypedQuery<Member> query = em.createQuery("select m.team from Member as m", Member.class);
            // 위와 동일
            // 이게 더 낫다. 위에는 예측이 좀 어렵다.
            TypedQuery<Team> query2 = em.createQuery("select t from Member as m join m.team t", Team.class);

            List<Team> resultList = query2.getResultList();

            // UPDATE 될까?
            // UPDATE 된다 -> 영속성 관리 대상
            Team findTeam = resultList.get(0);
            findTeam.setName("teamNameupdate");

            tx.commit();;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }
}
