package main.getResultList;

import jpql.Member;

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
            Member member = new Member();
            member.setUsername("kimseohae");
            member.setAge(10);

            em.persist(member);

            // as m 필수
            TypedQuery<Member> query = em.createQuery("select m from Member as m", Member.class);

            // 여러개일 경우
            List<Member> resultList = query.getResultList();

            // 1개 일 경우
            // 결과가 없거나 두개일 경우 에러 발생
            TypedQuery<Member> query2 = em.createQuery("select m from Member as m where m.id = 1", Member.class);
            Member result = query2.getSingleResult();
            // Spring Data JPA : 없을때 NULL을 반환하고 에러는 발생하지않음
            // Spring 은 위 에러를 try~catch 해서 처리함
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }
}
