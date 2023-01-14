package main.subquery;

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
            // JPA는 WHERE, HAVING 절에서만 서브 쿼리 사용 가능
            // 하이버네이트에서 서브쿼리 사용 가능
            // FROM 절의 서브 쿼리는 JQPL 에서 사용 불가능 (조인으로 풀 수 있으면 풀어서 해결)
            TypedQuery<Member> query = em.createQuery("select (select avg(m1) from Member m1) as avgAge from Member as m", Member.class);

            List<Member> resultList = query.getResultList();
            System.out.println("result.size = " + resultList.size());

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }
}
