package main.projection;

import jpql.Member;

import javax.persistence.*;
import java.util.List;

/**
 * 프로젝션 : SELECT 절에 조회할 대상을 지정하는것
 * 프로젝션 대상 : 엔티티, 임베디드 타입, 스칼라 타입(숫자, 문자 등 기본 데이터 타입)
 */
public class JpaMain {
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
            // 프로젝션시, 모든 데이터가 영속성 관리의 대상이다.
            TypedQuery<Member> query = em.createQuery("select m from Member as m", Member.class);

            List<Member> resultList = query.getResultList();

            // UPDATE 될까?
            // UPDATE 된다 -> 영속성 관리 대상
            Member findMember = resultList.get(0);
            findMember.setAge(20);

            tx.commit();;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }
}
